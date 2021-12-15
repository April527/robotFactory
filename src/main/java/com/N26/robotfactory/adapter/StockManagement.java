package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IStock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockManagement implements IStock {

    StockRepository stockRepository = new StockRepository();
    @Override
    public List<ComponentInventory> setStock() {

        return stockRepository.setInitialRobotPartStock();

    }

    @Override
    public List<ComponentInventory> getStock() {
        return stockRepository.getRobotPartStock();
    }
}
