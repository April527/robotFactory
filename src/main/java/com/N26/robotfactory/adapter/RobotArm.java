package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.gateway.IRobot;

public class RobotArm implements IRobot {

    public static final String HANDS = "HANDS";
    public static final String GRIPPERS = "GRIPPERS";


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
