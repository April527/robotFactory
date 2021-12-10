package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.gateway.IRobot;

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
    public void updateStock(String unitNumber) {
        stockRepository.setInitialRobotPartStock();
        stockRepository.updateRobotPartsStock(unitNumber);
    }
}
