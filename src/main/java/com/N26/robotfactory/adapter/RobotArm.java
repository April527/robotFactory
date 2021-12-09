package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.IRobot;

public class RobotArm implements IRobot {

    public static final String HANDS = "HANDS";
    public static final String GRIPPERS = "GRIPPERS";


    @Override
    public Double findPrice(String productCode) {

        return null;
    }

    @Override
    public void updateStock(String unitNumber) {

    }
}
