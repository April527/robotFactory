package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.IRobot;

public class EmptyRobot implements IRobot {

    @Override
    public Double findPrice(String productCode) {

        return null;
    }

    @Override
    public void updateStock(String unitNumber) {

    }
}
