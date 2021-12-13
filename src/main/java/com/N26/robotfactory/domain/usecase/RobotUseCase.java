package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.RobotFactory;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.PairedComponent;
import com.N26.robotfactory.gateway.IRobot;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@AllArgsConstructor
public class RobotUseCase {

    public Double placeRobotOrder(List<String> components) {

        return setComponentsList(components)
                .stream()
                .map(this::setInitialComponentList)
                .map(pairedElement -> updateStock(pairedElement.getT1(), components,pairedElement.getT2()))
                .map(component -> calculateFullRobotPrice(component.getComponentName(), component.getComponentCode()))
                .mapToDouble(a -> a)
                .sum();

    }

    private Tuple2<List<ComponentInventory>, PairedComponent>  setInitialComponentList( PairedComponent pairedComponent) {

        RobotFactory robotFactory = new RobotFactory();
        IRobot robotComponent = robotFactory.getRobotParts(pairedComponent.getComponentName());

        return Tuples.of(robotComponent.setStock(), pairedComponent);
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

    private PairedComponent updateStock( List<ComponentInventory> componentInventory,  List<String> components, PairedComponent pairedComponent) {
        RobotFactory robotFactory = new RobotFactory();
        IRobot robotComponent = robotFactory.getRobotParts(pairedComponent.getComponentName());
        robotComponent.updateStock( componentInventory , components);
        return pairedComponent;
    }

    private Double calculateFullRobotPrice(String componentName, String componentCode) {

        RobotFactory robotFactory = new RobotFactory(); //TODO: use @Autowired
        IRobot robotComponent = robotFactory.getRobotParts(componentName); //TODO: use @Autowired
        return robotComponent.findPrice(componentCode);

    }

}
