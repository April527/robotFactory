package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public interface IRobot {
    Mono<ComponentInventory> findPrice (List<ComponentInventory> componentInventory, String productCode);
    Mono<Void> updateStock(List<ComponentInventory> componentInventory, String componentCode);

}
