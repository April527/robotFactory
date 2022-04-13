package com.N26.robotfactory;

import com.N26.robotfactory.adapter.*;
import com.N26.robotfactory.data.StockRepository;
import com.N26.robotfactory.gateway.IRobot;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RobotFactory {

    @Autowired
    StockRepository stockRepository;

    public static final String FACE = "FACE";
    public static final String MATERIAL = "MATERIAL";
    public static final String ARM = "HANDS";
    public static final String MOBILITY = "MOBILITY";

    public IRobot getRobotParts(String robotPart){

        switch(robotPart){
            case FACE:
                return new RobotFace(stockRepository);
            case MATERIAL:
                return new RobotMaterial(stockRepository);
            case ARM:
                return new RobotArm(stockRepository);
            case MOBILITY:
                return new RobotMobility(stockRepository);
        }

        return new EmptyRobot();

    }
}
