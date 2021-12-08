package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.domain.model.Component;
import com.N26.robotfactory.domain.model.PairedElement;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@AllArgsConstructor
public class RobotUseCase {

    public String sellRobot(List<String> components) {

        final String FACE = "FACE";
        final String MATERIAL = "MATERIAL";
        final String ARM = "HANDS";
        final String MOBILITY = "MOBILITY";

        List<String> componentsName = Arrays.asList(FACE,MATERIAL,ARM,MOBILITY);

        List<PairedElement> pairedElementList = new ArrayList<>();

         IntStream.range(0,componentsName.size())
                                                .boxed()
                                                .flatMap(i ->{
                                                    pairedElementList.add(new PairedElement(componentsName.get(i), components.get(i)));
                                                    return Stream.of(pairedElementList) ;
                                                })
                                                .collect(Collectors.toList());

        return "total";
    }

}
