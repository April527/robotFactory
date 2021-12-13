package com.N26.robotfactory.data;

import com.N26.robotfactory.domain.model.BusinessException;
import com.N26.robotfactory.domain.model.ComponentInventory;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StockRepository {

    final List<ComponentInventory> robotPartStocks = new ArrayList<>();

    public static final String NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT = "The component doesn't exist or it's not available";

    public List<ComponentInventory> setInitialRobotPartStock(){

        robotPartStocks.add(new ComponentInventory("A", 10.28, 9, "Humanoid Face"));
        robotPartStocks.add(new ComponentInventory("B", 24.07, 7, "LCD Face"));
        robotPartStocks.add(new ComponentInventory("C", 13.30, 0, "Steampunk Face"));
        robotPartStocks.add(new ComponentInventory("D", 28.94, 1, "Arms with Hands"));
        robotPartStocks.add(new ComponentInventory("E", 12.39, 3, "Arms with Grippers"));
        robotPartStocks.add(new ComponentInventory("F", 30.77, 2, "Mobility with Wheels"));
        robotPartStocks.add(new ComponentInventory("G", 55.13, 15, "Arms with Hands"));
        robotPartStocks.add(new ComponentInventory("H", 50.00, 7, "Arms with Grippers"));
        robotPartStocks.add(new ComponentInventory("I", 90.12, 92, "Mobility with Wheels"));
        robotPartStocks.add(new ComponentInventory("J", 82.31, 15, "Mobility with Wheels"));
        return robotPartStocks;
    }

    public ComponentInventory findRobotPartStockByCode(List<ComponentInventory> componentInventory, String componentCode){
       return componentInventory.stream()
                .filter(x -> x.getCode().equals(componentCode))
                .findAny()
                .orElse(ComponentInventory.builder().build());
    }


    public void updateRobotPartsStock(List<ComponentInventory> componentInventory, List<String> components) {

        components
                .forEach(component -> {
                    if(componentExists(componentInventory, component) && isComponentAvailable(componentInventory, component)){
                        updateComponentStock(componentInventory, component);
                    } else {
                        throw new BusinessException(NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT);
                    }
                });

    }

    private void updateComponentStock(List<ComponentInventory> componentInventory, String component) {
        componentInventory.stream()
                .filter(componentInventory1 -> componentInventory1.getCode().equals(component))
                .forEach(x -> x.setAvailable(x.getAvailable() -1));
    }

    private boolean componentExists(List<ComponentInventory> componentInventory, String component) {
       return componentInventory.stream()
               .anyMatch(componentInventory1 -> componentInventory1.getCode().equals(component));
    }

    private boolean isComponentAvailable(List<ComponentInventory> componentInventory, String component) {

        return this.findRobotPartStockByCode(componentInventory, component).getAvailable()>0;

    }
}
