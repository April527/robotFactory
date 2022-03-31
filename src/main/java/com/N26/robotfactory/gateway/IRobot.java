package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IRobot {
    Mono<ComponentInventory> findRobotPart(List<ComponentInventory> componentInventory, String pairedComponents);
    Mono<Void> updateStock(List<ComponentInventory> componentInventory, String componentCode);

}
