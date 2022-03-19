package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.RobotFactory;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.PairedComponent;
import com.N26.robotfactory.domain.model.ResponseRobotFactory;
import com.N26.robotfactory.gateway.IRobot;
import com.N26.robotfactory.gateway.IStock;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@AllArgsConstructor
public class RobotUseCase {

    private RobotFactory robotFactory;
    private final IStock stockRepository;

     public Mono<ResponseRobotFactory> placeRobotOrder(List<String> components) {

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

         return Mono.just(pairedComponents)
                 .map(pairedComponent -> pairedComponent.stream()
                         .map(component -> Tuples.of(robotFactory.getRobotParts(component.get(0)), component))
                         .map(robotComponent -> robotComponent.getT1().updateStock(componentInventory, robotComponent.getT2().get(1))))
                 .then();
     /*  return Mono.just(robotFactory.getRobotParts(pairedComponents.get(0).get(0)))
                .flatMap(robotComponent-> robotComponent.updateStock(componentInventory, pairedComponents.get(0).get(1)))
                .then();*/


    }

    private Mono<BigDecimal> calculateFullRobotPrice(List<ComponentInventory> componentInventory, List<List<String>> pairedComponents) {

        return Mono.just(robotFactory.getRobotParts(pairedComponents.get(0).get(0)))
                .flatMap(robotComponent -> robotComponent.findPrice(componentInventory, pairedComponents.get(0).get(1)))
                .map(componentInventory1 -> componentInventory1.getPrice().setScale(2, RoundingMode.HALF_EVEN));



    }

}
