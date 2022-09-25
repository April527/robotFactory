package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.usecase.AlgorithmsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlgorithmsController {

    @Autowired
    AlgorithmsUseCase algorithmsUseCase;

    @GetMapping("/spiralMatrix3")
    public int[][] leetCodeAlgorithms () {

       return algorithmsUseCase.spiralMatrix3(5,6,1,4);

    }

    @GetMapping("/arithmeticSubarrays")
    public List<Boolean> arithmeticSubarrays () {

        int[] nums =  new int[]{4,6,5,9,3,7};
        int[] l =  new int[]{0,0,2};
        int[] r =  new int[]{2,3,5};

        return algorithmsUseCase.areArithmeticSubarrays(nums, l, r);

    }

}
