package com.N26.robotfactory;

import com.N26.robotfactory.adapter.*;
import com.N26.robotfactory.gateway.IRobot;
import org.springframework.stereotype.Component;

@Component
public class RobotFactory {

    public static final String FACE = "FACE";
    public static final String MATERIAL = "MATERIAL";
    public static final String ARM = "HANDS";
    public static final String MOBILITY = "MOBILITY";

    public IRobot getRobotParts(String robotPart){

        switch(robotPart){
            case FACE:
                return new RobotFace();
            case MATERIAL:
                return new RobotMaterial();
            case ARM:
                return new RobotArm();
            case MOBILITY:
                return new RobotMobility();
        }

        return new EmptyRobot();

    }
}
