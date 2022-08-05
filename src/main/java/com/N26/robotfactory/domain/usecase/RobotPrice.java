package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.domain.model.ComponentInventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public class RobotPrice {

    static Flux<BigDecimal> findRobotItemsPrice(ComponentInventory componentInventory, List<List<String>> pairedComponents) {

        return Flux.fromIterable(pairedComponents)
                .flatMap(pairedComponentList -> findRobotPart(componentInventory,pairedComponentList))
                .map(ComponentInventory::getPrice);

    }

    static Mono<ComponentInventory> findRobotPart(ComponentInventory componentInventory, List<String> componentName) {

        return (componentInventory.getCode().equals(componentName.get(1))) ? Mono.just(componentInventory):
                Mono.just(ComponentInventory.builder()
                        .price(new BigDecimal(0))
                        .build());

    }

}
