package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.gateway.IRobot;

public class RobotMaterial implements IRobot {

    public static final String BIOPLASTIC = "BIOPLASTIC";
    public static final String METALLIC = "METALLIC";

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
