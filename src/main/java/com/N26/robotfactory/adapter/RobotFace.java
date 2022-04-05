package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public class RobotFace implements IRobot {

    StockRepository stockRepository = new StockRepository();

    @Override
    public Mono<ComponentInventory> findRobotPart(List<ComponentInventory> componentInventory, String componentCode) {

        return stockRepository.findRobotPartStockByCode(componentInventory, componentCode);
    }

    @Override
    public Flux<ComponentInventory> updateStock(String componentCode) {

        return stockRepository.updateRobotPartsStock(componentCode);

    }
}
