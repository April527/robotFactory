package com.N26.robotfactory.adapter;

import com.N26.robotfactory.gateway.AbstractFactory;
import com.N26.robotfactory.gateway.ColorExample;

public class ArmsFactory implements AbstractFactory<Arm> {

    public static final String HANDS = "HANDS";
    public static final String GRIPPERS = "GRIPPERS";

    @Override
    public Arm create(String armType) {

        switch (armType) {
            case HANDS:
                return new Hand();
            case GRIPPERS:
                return new Gripper();

        }

        if ("Purple".equalsIgnoreCase(faceType)) {
            return new Purple();
        } else if ("Red".equalsIgnoreCase(faceType)) {
            return new Red();
        }

        return null;
    }
}
