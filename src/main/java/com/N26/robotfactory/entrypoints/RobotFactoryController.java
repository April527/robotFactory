package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.model.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api_robotFactory")
public class RobotFactoryController {

    @PostMapping("/orders")
    public String robotOrders(@RequestBody Component components) {

        return "total";
    }

}
