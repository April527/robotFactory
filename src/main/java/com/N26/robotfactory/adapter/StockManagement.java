package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class StockManagement implements IStock {

    @Autowired
    StockRepository stockRepository;

    @Override
    public List<ComponentInventory> setStock() {

        return stockRepository.setInitialRobotPartStock();

    }

    @Override
    public List<ComponentInventory> getStock() {
        return stockRepository.getRobotPartStock();
    }

    @Override
    public Flux<List<ComponentInventory>> rollbackInventory(String componentcode, List<List<String>> pairedComponents,
                                                      List<ComponentInventory> componentInventory) {
        return stockRepository.rollbackInventory(componentcode, pairedComponents, componentInventory);
    }
}
