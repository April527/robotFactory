package com.N26.robotfactory.adapter;

import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class EmptyRobot implements IRobot {



    @Override
    public Flux<ComponentInventory> updateStock(String componentCode) {
        return Flux.empty();
    }

    @Override
    public Mono<ComponentInventory> findRobotPart(List<ComponentInventory> componentInventory, String pairedComponents) {
        return null;
    }


}
