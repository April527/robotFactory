package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.IRobot;

public class RobotFace implements IRobot {

    public static final String HUMANOID = "HUMANOID";
    public static final String LCD = "LCD";
    public static final String STEAMPUNK = "STEAMPUNK";


    @Override
    public void findPrice(String productCode) {

    }

    @Override
    public void updateStock(String unitNumber) {

    }
}
