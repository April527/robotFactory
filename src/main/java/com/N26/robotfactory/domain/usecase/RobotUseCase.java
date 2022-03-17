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

    /* public ResponseRobotFactory placeRobotOrder(List<String> components) {

        return ResponseRobotFactory.builder()
                .order_id(generateOrder_Id())
                .total(getRobotFactoryTotal(components))
                .build();

    }*/

    private String generateOrder_Id(){
        return UUID.randomUUID().toString();
    }

    public Mono<BigDecimal> getRobotFactoryTotal(List<String> components) {

        List<List<String>> componentsList = setComponentsList(components);

       return Mono.just(stockRepository.getStock())
                .map(stock -> stock.isEmpty()? stockRepository.getStock(): stockRepository.setStock())
                .zipWhen(componentInventory -> updateStock(componentInventory, componentsList))
                .map(component -> calculateFullRobotPrice(component.getT1(), componentsList))
                .map(total -> total.setScale(2, RoundingMode.HALF_EVEN));

      /*  Mono.just(setComponentsList(components))
                .zipWhen(component -> stockRepository.getStock().isEmpty()? getRobotInventory(): setInitialComponentList())
                .zipWhen(tupleComponentInventory -> updateStock(tupleComponentInventory.getT2(), tupleComponentInventory.getT1()))
                .map(tupleComponentInventory2 -> calculateFullRobotPrice(tupleComponentInventory2.getT1().getT2(),
                        tupleComponentInventory2.getT1().getT1().get(0).get(0),  tupleComponentInventory2.getT1().getT1().get(0).get(1)))
                .map(price -> price.)
                .reduce(new BigDecimal(0), BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);*/

  /*      return setComponentsList(components)
                .stream()
                .map(pairedComponent -> getRobotInventory(pairedComponent.).getT1().isEmpty() ? setInitialComponentList(pairedComponent):getRobotInventory(pairedComponent))
                .map(pairedElement -> updateStock(pairedElement.getT1(), pairedElement.getT2()))
                .map(component -> calculateFullRobotPrice(component.getT2(), component.getT1().getComponentName(), component.getT1().getComponentCode()))
                .reduce(new BigDecimal(0), BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);*/
    }

    private Mono<List<ComponentInventory>> getRobotInventory() {
        return Mono.just(stockRepository.getStock());
    }

    private Mono<List<ComponentInventory>> setInitialComponentList() {

        return Mono.just(stockRepository.setStock());
    }

    private List<List<String>> setComponentsList(List<String> components) {

        final String FACE = "FACE";
        final String MATERIAL = "MATERIAL";
        final String ARM = "HANDS";
        final String MOBILITY = "MOBILITY";

        List<String> componentsName = Arrays.asList(FACE,MATERIAL,ARM,MOBILITY);

        List<List<String>> componentsTest = IntStream.range(0, componentsName.size())
                .boxed()
                .flatMap(i -> {
                    return Arrays.asList(Arrays.asList(componentsName.get(i), components.get(i))).stream();
                })
                .collect(Collectors.toList());

        componentsTest.forEach(System.out::println);

        return componentsTest;

   /*     return componentsName.stream()
                .flatMap(componentName -> components.stream().map(component -> Arrays.asList(componentName, component)))
                .collect(Collectors.toList());*/

    /*    List<PairedComponent> pairedComponentList = new ArrayList<>();

        IntStream.range(0,componentsName.size())
                .boxed()
                .flatMap(i ->{
                    pairedComponentList.add(new PairedComponent(componentsName.get(i), components.get(i)));
                    return Stream.of(pairedComponentList) ;
                })
                .collect(Collectors.toList());

        return pairedComponentList;*/
    }

    private Mono<Void> updateStock(List<ComponentInventory> componentInventory, List<List<String>> pairedComponents) {

        //pairedComponents.forEach(System.out::println);

       /* IRobot robotComponent = robotFactory.getRobotParts(pairedComponents.get(0).get(0));
        robotComponent.updateStock( componentInventory , pairedComponents.get(0).get(1));*/

        return Mono.empty();

    }

    private BigDecimal calculateFullRobotPrice(List<ComponentInventory> componentInventory, List<List<String>> pairedComponents) {

   /*     IRobot robotComponent = robotFactory.getRobotParts(componentName);
        return robotComponent.findPrice(componentInventory, componentCode);*/

        return new BigDecimal(12);

    }

}
