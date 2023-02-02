package com.N26.robotfactory.entrypoints;

import com.N26.robotfactory.domain.model.Node;
import com.N26.robotfactory.domain.model.TreeNode;
import com.N26.robotfactory.domain.usecase.AlgorithmsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
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

        int[] nums =  new int[]{-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10};
        int[] l =  new int[]{0,1,6,4,8,7};
        int[] r =  new int[]{4,4,9,7,9,10};

        return algorithmsUseCase.areArithmeticSubarrays(nums, l, r);

    }

    @GetMapping("/printBinaryTree")
    public List<List<String>> printBinaryTree () {
        List<Integer> nodos = Arrays.asList(1,2,3,4);

        return algorithmsUseCase.printBinaryTree(algorithmsUseCase.createBinaryTree());

    }

    @GetMapping("/search2DMatrix")
    public boolean  search2DMatrix () {

        return algorithmsUseCase.search2DMatrix(algorithmsUseCase.initializeMatrix(3,3), 3);

    }

    @GetMapping("/getPathMaximumGold")
    public int  getPathMaximumGold () {

        return algorithmsUseCase.getPathMaximumGold(algorithmsUseCase.initializeMatrix(3,3));

    }

    @GetMapping("/dailyTemperatures")
    public int[]  dailyTemperatures () {

        int[] temperatures =  new int[]{73,74,75,71,69,72,76,73};

        return algorithmsUseCase.dailyTemperatures(temperatures);

    }

    @GetMapping("/maxLevelSum")
    public int  maxLevelSum () {

        Node binaryTree = algorithmsUseCase.createBinaryTree();

        return algorithmsUseCase.maxLevelSum(binaryTree);

    }

    @GetMapping("/rightSideView")
    public List<Integer> rightSideView () {

        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)), new TreeNode(3,
                null, new TreeNode(4)
        ));

        return algorithmsUseCase.rightSideView(root);

    }

    @GetMapping("/letterCombinations")
    public List<String> letterCombinations () {

        List<String> result = new ArrayList<String>();

        String[] mapping = {
                "0",
                "1",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

        algorithmsUseCase.letterCombinationsRecursive(result, "23", "",0, mapping);
        return result;

    }

    @GetMapping("/topKFrequentElements")
    public int[] topKFrequentElements () {

        int[] nums =  new int[]{1,1,1,2,2,3};

        return algorithmsUseCase.topKFrequentElements(nums, 2);

    }

}
