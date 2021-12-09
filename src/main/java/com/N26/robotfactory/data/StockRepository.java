package com.N26.robotfactory.data;

import com.N26.robotfactory.domain.model.Component;
import com.N26.robotfactory.domain.model.ComponentInventory;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

        return robotPartStocks;
    }

    public ComponentInventory findRobotPartStockByCode(String componentCode){
       return robotPartStocks.stream()
                .filter(x -> x.getCode().equals(componentCode))
                .findAny()
                .orElse(ComponentInventory.builder().build());
    }

}
