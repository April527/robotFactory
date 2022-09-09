package com.N26.robotfactory.domain.usecase;

public class AlgorithmsUseCase {

    public int[][] spiralMatrix3(int RowsNumber, int ColumnsNumber, int x, int y) {

                int[][] res = new int[RowsNumber * ColumnsNumber][2];
                int moveX = 0, moveY = 1, n = 0, tmp;

                for (int j = 0; j < RowsNumber * ColumnsNumber; ++n) { //TODO Find out what does "n" do
                    for (int i = 0; i < n / 2 + 1; ++i) {

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

}
