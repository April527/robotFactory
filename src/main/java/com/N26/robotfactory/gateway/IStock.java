package com.N26.robotfactory.gateway;

import com.N26.robotfactory.domain.model.ComponentInventory;

import java.util.List;

public interface IStock {
    List<ComponentInventory> setStock();

    List<ComponentInventory> getStock();
}
