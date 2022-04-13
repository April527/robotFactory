package com.N26.robotfactory.adapter;

import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class RobotMaterial implements IRobot {

    @Autowired
    StockRepository stockRepository;

    @Override
    public Mono<ComponentInventory> findRobotPart(List<ComponentInventory> componentInventory, String componentCode) {

        return stockRepository.findRobotPartStockByCode(componentInventory, componentCode);
    }

    @Override
    public Flux<ComponentInventory> updateStock(String componentCode) {

        return stockRepository.updateRobotPartsStock(componentCode);

    }

}
