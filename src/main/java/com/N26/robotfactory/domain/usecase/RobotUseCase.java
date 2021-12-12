package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.RobotFactory;
import com.N26.robotfactory.domain.model.PairedComponent;
import com.N26.robotfactory.gateway.IRobot;
import lombok.AllArgsConstructor;

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
                .map(pairedComponent -> calculateFullRobotPrice(pairedComponent.getComponentName(), pairedComponent.getComponentCode()))
                .mapToDouble(a -> a)
                .sum();


     //    updateStock(FACE, "A");

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

    private void setOrUpdateStock(PairedComponent pairedComponent) {
        RobotFactory robotFactory = new RobotFactory();
        IRobot robotComponent = robotFactory.getRobotParts(pairedComponent.getComponentName());
        robotComponent.updateStock(pairedComponent.getComponentCode());
    }

    private Double calculateFullRobotPrice(String componentName, String componentCode) {

        RobotFactory robotFactory = new RobotFactory(); //TODO: use @Autowired
        IRobot robotComponent = robotFactory.getRobotParts(componentName); //TODO: use @Autowired
        return robotComponent.findPrice(componentCode);

    }

}
