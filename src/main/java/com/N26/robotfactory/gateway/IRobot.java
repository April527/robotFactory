package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IRobot {

    Flux<ComponentInventory> updateStock(String componentCode);
    Mono<ComponentInventory> findRobotPart(List<ComponentInventory> componentInventory, String pairedComponents);


}
