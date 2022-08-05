package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.domain.model.ComponentInventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RobotPrice {

    static Mono<BigDecimal> findRobotItemsPrice(ComponentInventory componentInventory, List<List<String>> pairedComponents) {

        return Flux.fromIterable(pairedComponents)
                .flatMap(pairedComponentList -> findRobotPart(componentInventory,pairedComponentList))
                .map(ComponentInventory::getPrice)
                .reduce(new BigDecimal(0), BigDecimal::add)
                .map(totalRobotPrice -> totalRobotPrice.setScale(2, RoundingMode.HALF_UP));

    }

    static Mono<ComponentInventory> findRobotPart(ComponentInventory componentInventory, List<String> componentName) {

        return (componentInventory.getCode().equals(componentName.get(1))) ? Mono.just(componentInventory):
                Mono.just(ComponentInventory.builder()
                        .price(new BigDecimal(0))
                        .build());

    }

}
