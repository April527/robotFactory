package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.domain.model.ComponentInventory;

import java.util.List;
import java.util.stream.Collectors;

public class ValidateRobotOrder {

    static boolean validateRobotOrder(List<ComponentInventory> componentInventory1, List<List<String>> componentsList) {

        List<List<String>> robotOrderList = componentsList.stream()
                .filter(component ->
                        componentInventory1.stream()
                                .anyMatch(compInventory1 -> compInventory1.getCode().equals(component.get(1)) &&
                                        compInventory1.getPart().toLowerCase().contains(component.get(0).toLowerCase())))
                .collect(Collectors.toList());

        return robotOrderList.equals(componentsList);
    }

}
