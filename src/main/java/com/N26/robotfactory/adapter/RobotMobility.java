package com.N26.robotfactory.adapter;


import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.gateway.IRobot;

public class RobotMobility implements IRobot {

    public static final String WHEELS = "WHEELS";
    public static final String LEGS = "LEGS";
    public static final String TRACKS = "TRACKS";

    StockRepository stockRepository = new StockRepository();

    @Override
    public Double findPrice(String productCode) {

        stockRepository.setInitialRobotPartStock();
        return stockRepository.findRobotPartStockByCode(productCode).getPrice();
    }

    @Override
    public void updateStock(String unitNumber) {

    }
}
