package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api_robotFactory")
public class RobotFactoryController {

    @PostMapping("/customizedRobot/calculatePrice")
    public String novedadCambioPiso(@RequestBody Component components) {
        return "total";
    }

}
