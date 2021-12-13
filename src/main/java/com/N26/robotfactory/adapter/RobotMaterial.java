package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.domain.model.PairedComponent;
import com.N26.robotfactory.gateway.IRobot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


public class RobotMaterial implements IRobot {

    StockRepository stockRepository = new StockRepository();

    @Override
    public Double findPrice(List<ComponentInventory> componentInventory, String productCode) {

        return stockRepository.findRobotPartStockByCode(componentInventory, productCode).getPrice();
    }

    @Override
    public List<ComponentInventory> setStock() {

         return  stockRepository.setInitialRobotPartStock();

    }

    @Override
    public void updateStock( List<ComponentInventory> componentInventory, List<String> components) {

        stockRepository.updateRobotPartsStock(componentInventory, components);

    }
}
