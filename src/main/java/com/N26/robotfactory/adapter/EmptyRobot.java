package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.IRobot;

public class EmptyRobot implements IRobot {

    @Override
    public void findPrice(String productCode) {

    }

    @Override
    public void updateStock(String unitNumber) {

    }
}
