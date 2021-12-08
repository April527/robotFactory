package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.model.Component;
import com.N26.robotfactory.domain.usecase.RobotUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api_robotFactory")
public class RobotFactoryController {

   /* @Autowired
    RobotUseCase robotUseCase;*/

    RobotUseCase robotUseCase= new RobotUseCase();

    @PostMapping("/orders")
    public String robotOrders(@RequestBody Component components) {


        return robotUseCase.sellRobot(components.getComponents());
    }

}
