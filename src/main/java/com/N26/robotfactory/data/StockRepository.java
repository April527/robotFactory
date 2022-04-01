package com.N26.robotfactory.data;

import com.N26.robotfactory.domain.model.BusinessException;
import com.N26.robotfactory.domain.model.ComponentInventory;
import lombok.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StockRepository {

    final static List<ComponentInventory> robotPartStocks = new ArrayList<>();

    public static final String NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT = "The component doesn't exist or it's not available";

    public List<ComponentInventory> setInitialRobotPartStock(){
//TODO Write a method that rounds the bigDecimal to two decimal numbers
        robotPartStocks.add(new ComponentInventory("A", new BigDecimal(10.28), 9, "Humanoid Face"));
        robotPartStocks.add(new ComponentInventory("B", new BigDecimal(24.07), 7, "LCD Face"));
        robotPartStocks.add(new ComponentInventory("C", new BigDecimal(13.30), 0, "Steampunk Face"));
        robotPartStocks.add(new ComponentInventory("D", new BigDecimal(28.94), 1, "Arms with Hands"));
        robotPartStocks.add(new ComponentInventory("E", new BigDecimal(12.39), 3, "Arms with Grippers"));
        robotPartStocks.add(new ComponentInventory("F", new BigDecimal(30.77), 2, "Mobility with Wheels"));
        robotPartStocks.add(new ComponentInventory("G", new BigDecimal(55.13), 15, "Mobility with Legs"));
        robotPartStocks.add(new ComponentInventory("H", new BigDecimal(50.00), 7, "Mobility with Tracks"));
        robotPartStocks.add(new ComponentInventory("I", new BigDecimal(90.12), 92, "Material Bioplastic"));
        robotPartStocks.add(new ComponentInventory("J", new BigDecimal(82.31), 15, "Material Metallic"));
        return robotPartStocks;
    }

    public Mono<ComponentInventory> findRobotPartStockByCode(List<ComponentInventory> componentInventory, String componentCode){

       return Mono.just(componentInventory)
                .map(componentInventoryList -> {
                   return componentInventoryList.stream()
                            .filter(component -> component.getCode().equals(componentCode))
                            .findAny()
                            .orElseThrow(() -> new BusinessException(NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT + "1"));
                })
                .map(componentInventory1 -> componentInventory1);
    }


    public synchronized Mono<Void> updateRobotPartsStock(List<ComponentInventory> componentInventory, String componentCode) {

        return Flux.fromIterable(componentInventory)
                .filter(componentInventory1 -> componentInventory1.getCode().equals(componentCode))
                .map(x -> {
                    x.setAvailable(x.getAvailable() - 1);
                    return x;
                })
                .then();
    }


    public List<ComponentInventory> getRobotPartStock() {
        return robotPartStocks;
    }
}
