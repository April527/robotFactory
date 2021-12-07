package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.IRobot;

public class EmptyRobot implements IRobot {
    @Override
    public void findPrice() {
        System.out.println("THERE WEREN'T ENOUGH PARTS SPECIFIED");
    }

    @Override
    public void updateStock() {
        System.out.println("THERE WEREN'T ENOUGH PARTS SPECIFIED");
    }
}
