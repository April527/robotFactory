package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.RobotFactory;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.PairedComponent;
import com.N26.robotfactory.domain.model.ResponseRobotFactory;
import com.N26.robotfactory.gateway.IRobot;
import com.N26.robotfactory.gateway.IStock;
import lombok.AllArgsConstructor;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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

    public ResponseRobotFactory placeRobotOrder(List<String> components) {

        return ResponseRobotFactory.builder()
                .order_id(generateOrder_Id())
                .total(getRobotFactoryTotal(components))
                .build();

    }

    private String generateOrder_Id(){
        return UUID.randomUUID().toString();
    }

    private Double getRobotFactoryTotal(List<String> components) {
        return setComponentsList(components)
                .stream()
                .map(pairedComponent -> getRobotInventory(pairedComponent).getT1().isEmpty() ? setInitialComponentList(pairedComponent):getRobotInventory(pairedComponent))
                .map(pairedElement -> updateStock(pairedElement.getT1(), components,pairedElement.getT2()))
                .map(component -> calculateFullRobotPrice(component.getT2(), component.getT1().getComponentName(), component.getT1().getComponentCode()))
                .mapToDouble(price -> price)
                .sum();
    }

    private Tuple2<List<ComponentInventory>, PairedComponent> getRobotInventory(PairedComponent pairedComponent) {
        return Tuples.of(stockRepository.getStock(), pairedComponent);
    }

    private Tuple2<List<ComponentInventory>, PairedComponent>  setInitialComponentList( PairedComponent pairedComponent) {

        return Tuples.of(stockRepository.setStock(), pairedComponent);
    }

    private List<PairedComponent> setComponentsList(List<String> components) {

        final String FACE = "FACE";
        final String MATERIAL = "MATERIAL";
        final String ARM = "HANDS";
        final String MOBILITY = "MOBILITY";

        List<String> componentsName = Arrays.asList(FACE,MATERIAL,ARM,MOBILITY);

        List<PairedComponent> pairedComponentList = new ArrayList<>();

        IntStream.range(0,componentsName.size())
                .boxed()
                .flatMap(i ->{
                    pairedComponentList.add(new PairedComponent(componentsName.get(i), components.get(i)));
                    return Stream.of(pairedComponentList) ;
                })
                .collect(Collectors.toList());

        return pairedComponentList;
    }

    private Tuple2<PairedComponent, List<ComponentInventory>> updateStock(List<ComponentInventory> componentInventory, List<String> components, PairedComponent pairedComponent) {

        IRobot robotComponent = robotFactory.getRobotParts(pairedComponent.getComponentName());
        robotComponent.updateStock( componentInventory , components);
        return Tuples.of(pairedComponent, componentInventory);
    }

    private Double calculateFullRobotPrice(List<ComponentInventory> componentInventory, String componentName, String componentCode) {

        IRobot robotComponent = robotFactory.getRobotParts(componentName);
        return robotComponent.findPrice(componentInventory, componentCode);

    }

}
