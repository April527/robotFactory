package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.model.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api_robotFactory")
public class RobotFactoryController {

    @PostMapping("/customizedRobot/calculatePrice")
    public String novedadCambioPiso(@RequestBody Component components) {
        return "total";
    }

}
