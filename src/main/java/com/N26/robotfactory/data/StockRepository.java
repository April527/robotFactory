package com.N26.robotfactory.data;

import com.N26.robotfactory.domain.model.BusinessException;
import com.N26.robotfactory.domain.model.ComponentInventory;
import lombok.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Repository
public class StockRepository {

    static List<ComponentInventory> robotPartStocks = new ArrayList<>();

    public static final String NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT = "The component doesn't exist or it's not available";

    public List<ComponentInventory> setInitialRobotPartStock(){

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
                            .orElseThrow(() -> new BusinessException(NON_AVAILABLE_OR_NON_EXISTENT_COMPONENT));
                })
                .map(componentInventory1 -> componentInventory1);
    }


    public synchronized Flux<ComponentInventory> updateRobotPartsStock(String componentCode) {

        return Flux.fromIterable(this.getRobotPartStock())
                .filter(robotPartStock -> robotPartStock.getCode().equals(componentCode))
                .map(x -> {
                    x.setAvailable(x.getAvailable() - 1);
                    return x;
                });
    }

    public List<ComponentInventory> getRobotPartStock() {
        return robotPartStocks;
    }

    public Flux<List<ComponentInventory>> rollbackInventory (String componentCode, List<List<String>> pairedComponents,
                                                       List<ComponentInventory> componentInventory){

        return Flux.fromIterable(pairedComponents)
                .filter(pairedComponent -> !pairedComponent.get(1).equals(componentCode))
                .map(pairedComponent1 -> setInventoryRollback(componentInventory, pairedComponent1));
    }

    private List<ComponentInventory> setInventoryRollback(List<ComponentInventory> componentInventory, List<String> pairedComponent1) {
        return componentInventory.stream()
                .filter(componentInventory1 -> componentInventory1.getCode().equals(pairedComponent1.get(1)))
                .map(componentInventory2 -> {
                    componentInventory2.setAvailable(componentInventory2.getAvailable() + 1);
                    return componentInventory2;
                })
                .collect(Collectors.toList());
    }


}
