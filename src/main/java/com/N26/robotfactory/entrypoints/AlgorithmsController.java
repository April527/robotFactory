package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.usecase.AlgorithmsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmsController {

    @Autowired
    AlgorithmsUseCase algorithmsUseCase;

    @GetMapping("/leetCodeAlgorithms")
    public int[][] leetCodeAlgorithms () {

       return algorithmsUseCase.spiralMatrix3(5,6,1,4);

    }

}
