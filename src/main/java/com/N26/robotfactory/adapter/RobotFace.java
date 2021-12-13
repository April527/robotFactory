package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.PairedComponent;
import com.N26.robotfactory.gateway.IRobot;

import java.util.List;

public class RobotFace implements IRobot {

    public static final String HUMANOID = "HUMANOID";
    public static final String LCD = "LCD";
    public static final String STEAMPUNK = "STEAMPUNK";

    StockRepository stockRepository = new StockRepository();

    @Override
    public Double findPrice(String productCode) {

        stockRepository.setInitialRobotPartStock();
        return stockRepository.findRobotPartStockByCode(productCode).getPrice();
    }

    @Override
    public List<ComponentInventory> setStock() {

       return stockRepository.setInitialRobotPartStock();

    }

    @Override
    public void updateStock( List<ComponentInventory> componentInventory, List<String> components) {

        stockRepository.updateRobotPartsStock(componentInventory, components);

    }
}
