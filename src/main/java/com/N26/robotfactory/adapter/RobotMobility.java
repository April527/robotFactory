package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;

import java.math.BigDecimal;
import java.util.List;

public class RobotMobility implements IRobot {

    StockRepository stockRepository = new StockRepository();

    @Override
    public BigDecimal findPrice(List<ComponentInventory> componentInventory, String productCode) {

        return stockRepository.findRobotPartStockByCode(componentInventory, productCode).getPrice();
    }

    @Override
    public void updateStock( List<ComponentInventory> componentInventory, String componentCode) {

        stockRepository.updateRobotPartsStock(componentInventory, componentCode);

    }
}
