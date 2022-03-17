package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.model.Component;
import com.N26.robotfactory.domain.model.ResponseRobotFactory;
import com.N26.robotfactory.domain.usecase.RobotUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
public class RobotFactoryController {

    @Autowired
    RobotUseCase robotUseCase;

    @PostMapping("/orders")
    public Mono<BigDecimal> robotOrders(@RequestBody Component components) {

        //return robotUseCase.placeRobotOrder(components.getComponents());

        return robotUseCase.getRobotFactoryTotal(components.getComponents());
    }

}
