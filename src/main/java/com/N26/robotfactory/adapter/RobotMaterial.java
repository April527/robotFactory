package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.PairedComponent;
import com.N26.robotfactory.gateway.IRobot;

import java.util.List;

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
    public List<ComponentInventory> setStock() {

         return  stockRepository.setInitialRobotPartStock();

    }

    @Override
    public PairedComponent updateStock( PairedComponent pairedComponent) {

        stockRepository.updateRobotPartsStock(pairedComponent.getComponentCode());

        return pairedComponent;
    }
}
