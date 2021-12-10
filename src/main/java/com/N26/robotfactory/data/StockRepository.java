package com.N26.robotfactory.data;

import com.N26.robotfactory.domain.model.Component;
import com.N26.robotfactory.domain.model.ComponentInventory;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRepository {

     List<ComponentInventory> robotPartStocks = new ArrayList<>();

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

    public ComponentInventory findRobotPartStockByCode(String componentCode){
       return robotPartStocks.stream()
                .filter(x -> x.getCode().equals(componentCode))
                .findAny()
                .orElse(ComponentInventory.builder().build());
    }

    public void updateRobotPartsStock(String componentCode) {
         robotPartStocks.stream()
                 .forEach(x ->{
                     if (x.getCode().equals(componentCode)){
                         x.setAvailable(x.getAvailable() - 1);
                     }
                 });

        Integer whatever = 1;
    }
}
