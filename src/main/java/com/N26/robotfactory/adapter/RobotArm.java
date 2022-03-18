package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public class RobotArm implements IRobot {

    StockRepository stockRepository = new StockRepository();

    @Override
    public Mono<ComponentInventory> findPrice(List<ComponentInventory> componentInventory, String productCode) {

        return stockRepository.findRobotPartStockByCode(componentInventory, productCode);
    }

    @Override
    public Mono<Void> updateStock(List<ComponentInventory> componentInventory, String componentCode) {

        return stockRepository.updateRobotPartsStock(componentInventory, componentCode);

    }
}
