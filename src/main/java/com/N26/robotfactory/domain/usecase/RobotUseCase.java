package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.RobotFactory;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.ResponseRobotFactory;
import com.N26.robotfactory.gateway.IRobot;
import com.N26.robotfactory.gateway.IStock;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class RobotUseCase {

    private RobotFactory robotFactory;
    private final IStock stockRepository;

     public Mono<ResponseRobotFactory> placeRobotOrder(List<String> components) {
// TODO I could try streaming componentsList and apply the operations to each list element

         List<List<String>> componentsList = setComponentsList(components);

         return Mono.just(stockRepository.getStock())
                 .map(stock -> stock.isEmpty()? stockRepository.setStock(): stockRepository.getStock())
                 .zipWhen(componentInventory -> updateStock(componentInventory, componentsList))
                 .flatMap(component -> calculateFullRobotPrice(component.getT1(), componentsList))
                 .map(total -> buildRobotResponse(total));

    }

    private String generateOrder_Id(){
        return UUID.randomUUID().toString();
    }

    private ResponseRobotFactory buildRobotResponse(BigDecimal total) {
        return ResponseRobotFactory.builder()
                .order_id(generateOrder_Id())
                .total(total)
                .build();
    }

    private List<List<String>> setComponentsList(List<String> components) {
//TODO Throw exception when the order is invalid
        final String MATERIAL = "MATERIAL";
        final String FACE = "FACE";
        final String ARM = "HANDS";
        final String MOBILITY = "MOBILITY";

        List<String> componentsName = Arrays.asList(MATERIAL,FACE,ARM, MOBILITY);

        List<List<String>> componentsTest = IntStream.range(0, componentsName.size())
                .boxed()
                .flatMap(i -> {
                    return Arrays.asList(Arrays.asList(componentsName.get(i), components.get(i))).stream();
                })
                .collect(Collectors.toList());

        return componentsTest;

    }

    private Mono<Void> updateStock(List<ComponentInventory> componentInventory, List<List<String>> pairedComponents) {
// Todo controlar cuando el filtro esta vacio
         return Flux.fromIterable(pairedComponents)
                 .filterWhen(component -> isComponentAvailable(componentInventory, component))
                 .filterWhen(robotComponent -> componentExists(componentInventory, robotComponent))
                 .map(robotComponent1 -> updateRobotStock(componentInventory, robotComponent1))
                 .then();

      /*   return Mono.just(pairedComponents)
                 .map(pairedComponent -> pairedComponent.stream()
                         .filter(component ->  isComponentAvailable(componentInventory, component))
                         .map(component -> Tuples.of(robotFactory.getRobotParts(component.get(0)), component))
                         .map(robotComponent -> robotComponent.getT1().updateStock(componentInventory, robotComponent.getT2().get(1))))
                 .then();*/


    }

    private Mono<Void> updateRobotStock(List<ComponentInventory> componentInventory, List<String> robotComponent) {

        IRobot robotPart = robotFactory.getRobotParts(robotComponent.get(0));

        return robotPart.updateStock(componentInventory, robotComponent.get(1));
    }

    private Mono<Boolean> componentExists(List<ComponentInventory> componentInventory, List<String> component) {

        return Mono.just(componentInventory)
                .map(componentInventoryList -> componentInventoryList.stream()
                        .anyMatch(componentInventory1 -> componentInventory1.getCode().equals(component.get(1))));

    }

    private Mono<Boolean> isComponentAvailable(List<ComponentInventory> componentInventory, List<String> component) {

        IRobot robotPart = robotFactory.getRobotParts(component.get(0));

         return robotPart.findRobotPart(componentInventory, component.get(1))
                 .map(componentInventory1 -> componentInventory1.getAvailable() > 0);
    }

    private Mono<BigDecimal> calculateFullRobotPrice(List<ComponentInventory> componentInventory, List<List<String>> pairedComponents) {

         return Flux.fromIterable(pairedComponents)
                 .flatMap(pairedComponentList -> findRobotPart(componentInventory,pairedComponentList.get(0)))
                 .map(componentInventory1 -> componentInventory1.getPrice())
                 .reduce(new BigDecimal(0), BigDecimal::add);

    }

    private Mono<ComponentInventory> findRobotPart(List<ComponentInventory> componentInventory, String componentName) {

         return Mono.just(robotFactory.getRobotParts(componentName))
                 .flatMap(robotComponent -> robotComponent.findRobotPart(componentInventory, componentName))
                 .map(componentInventory1 -> componentInventory1);
    }

}
