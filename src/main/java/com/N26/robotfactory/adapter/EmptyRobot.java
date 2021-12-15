package com.N26.robotfactory.adapter;

import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;

import java.util.List;

public class EmptyRobot implements IRobot {


    @Override
    public Double findPrice(List<ComponentInventory> componentInventory,String productCode) {

        return null;
    }

    @Override
    public void updateStock( List<ComponentInventory> componentInventory, List<String> components) {

    }
}
