package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.domain.model.BinaryTree;
import com.N26.robotfactory.domain.model.Node;

import java.util.*;

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

        List<Boolean> arithmeticSequenceList = new ArrayList<>();

        for (int i = 0 ; i < l.length ; i++){

            if(l[i] < nums.length && r[i] < nums.length && l[i] < r[i]) {

                int[] subArrayStream = Arrays.stream(nums, l[i], r[i] + 1)
                        .toArray();

                arithmeticSequenceList.add(isArithmeticSequence(subArrayStream));
            }
        }

        return arithmeticSequenceList;

    }

    public Boolean isArithmeticSequence(int[] orderedSubarray) {

        Arrays.sort(orderedSubarray);

        int difference = orderedSubarray[1] - orderedSubarray[0];
        boolean isSequenceSymetrical = false;

        for(int i = 0 ; i < orderedSubarray.length ; i++){

            if (i+1 < orderedSubarray.length) {
                isSequenceSymetrical = orderedSubarray[i + 1] - orderedSubarray[i] == difference;
            }
        }

        return isSequenceSymetrical;
    }

    public Node createBinaryTree() {

        BinaryTree bt = new BinaryTree();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);

        return bt.getRoot();
    }

    public List<List<String>> printBinaryTree(Node root) {

        List<List<String>> res = new LinkedList<>();

        int height = root == null ? 1 : findHeight(root);

        int rows = height, columns = (int) (Math.pow(2, height) - 1);

        List<String> row = new ArrayList<>();

        for(int i = 0; i < columns; i++)  row.add("");

        for(int i = 0; i < rows; i++)  res.add(new ArrayList<>(row));

        populateRes(root, res, 0, rows, 0, columns - 1);

        return res;

    }

    private int findHeight(Node root) {
        //Check whether tree is empty
        if(root == null) {
            System.out.println("Tree is empty");
            return 0;
        }
        else {
            int leftHeight = 0, rightHeight = 0;

            //Calculate the height of left subtree
            if(root.getLeft() != null)
                leftHeight = findHeight(root.getLeft());

            //Calculate the height of right subtree
            if(root.getRight() != null)
                rightHeight = findHeight(root.getRight());

            //Compare height of left subtree and right subtree
            //and store maximum of two in variable max
            int max = (leftHeight > rightHeight) ? leftHeight : rightHeight;

            //Calculate the total height of tree by adding height of root
            return (max+1);
        }
    }

    public void populateRes(Node root, List<List<String>> res, int row, int totalRows, int i, int j) {
        if (row == totalRows || root == null) return;
        res.get(row).set((i+j)/2, Integer.toString(root.getValue()));
        populateRes(root.getLeft(), res, row+1, totalRows, i, (i+j)/2 - 1);
        populateRes(root.getRight(), res, row+1, totalRows, (i+j)/2+1, j);
    }
}
