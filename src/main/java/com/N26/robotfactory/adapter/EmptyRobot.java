package com.N26.robotfactory.adapter;

import com.N26.robotfactory.domain.model.ComponentInventory;
import com.N26.robotfactory.gateway.IRobot;

import java.math.BigDecimal;
import java.util.List;

public class EmptyRobot implements IRobot {


    @Override
    public BigDecimal findPrice(List<ComponentInventory> componentInventory, String productCode) {

        return null;
    }

    @Override
    public void updateStock( List<ComponentInventory> componentInventory, String componentCode) {

    }
}
