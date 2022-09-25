package com.N26.robotfactory.domain.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class AlgorithmsUseCase {

    public int[][] spiralMatrix3(int RowsNumber, int ColumnsNumber, int x, int y) {

                int[][] res = new int[RowsNumber * ColumnsNumber][2];
                int moveX = 0, moveY = 1, n = 0, tmp;

                for (int j = 0; j < RowsNumber * ColumnsNumber; ++n) { //n helps control the length of the step [1,1,2,2,3,3....]
                    for (int i = 0; i < (n / 2) + 1; ++i) {

                        if (0 <= x && x < RowsNumber && 0 <= y && y < ColumnsNumber) {
                            res[j++] = new int[] {x, y};
                        }

                        x += moveX;
                        y += moveY;
                    }
                    tmp = moveX;
                    moveX = moveY;
                    moveY = -tmp;
                }

                return res;
    }

    public List<Boolean> areArithmeticSubarrays(int[] nums, int[] l, int[] r) {

        for (int i = 0 ; i < l.length ; i++){

            if(l[i] < nums.length && r[i] < nums.length && l[i] < r[i]) {

                int[] subArrayStream = Arrays.stream(nums, l[i], r[i] + 1)
                        .toArray();

                Arrays.sort(subArrayStream);

                //TODO: Go over the sorted array, see if there's an arithmetic sequence and fill the boolean list depending on the case
                // It can be done with an Arrays.stream().sorted().map(i -> nums(i) - nums(i+1)... or find out how to do it

                String ale = "";
            }
        }

        return new ArrayList<>();

    }
}
