package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IStock {
    List<ComponentInventory> setStock();

    List<ComponentInventory> getStock();

    Flux<List<ComponentInventory>> rollbackInventory(String componentCode, List<List<String>> pairedComponents,
                                               List<ComponentInventory> componentInventory);
}
