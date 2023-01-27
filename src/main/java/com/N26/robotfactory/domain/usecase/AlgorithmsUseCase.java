package com.N26.robotfactory.domain.usecase;

import com.N26.robotfactory.domain.model.BinaryTree;
import com.N26.robotfactory.domain.model.Node;
import com.N26.robotfactory.domain.model.TreeNode;

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
        bt.add(null);
        bt.add(5);
        bt.add(null);
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

    public boolean search2DMatrix(int[][] matrix, int target) {

        if (matrix.length == 0) return false;

        int rows = matrix.length;
        int columns = matrix[0].length;

        int left = 0;
        int right = (rows * columns) - 1;

        while (left <= right){

            int midpoint = left + (right - left) / 2;
            int midpoint_element = matrix[midpoint/columns][midpoint%columns];

            if(midpoint_element == target) {
                return true;
            } else if (target < midpoint_element) {
                right = midpoint - 1;
            } else if (target > midpoint_element) {
                left = midpoint + 1;
            }
        }

    return false;

    }

    public int[][] initializeMatrix(int rows, int columns) {

        int[][] matrix = { { 0, 6, 0},
                { 5, 8, 7 },
                {0, 9, 1} };


        return matrix;
    }

    public int getPathMaximumGold(int[][] grid) {

        int maxSum = 0;
        int row = grid.length;
        int col = grid[0].length;

        for (int i=0; i < row; i++){
            for (int j=0; j < col; j++){
                if (grid[i][j] > 0){

                    int sum = dfs(grid, i, j, row, col);
                    maxSum = Math.max(sum, maxSum);
                }
            }

        }

        return maxSum;
    }

    private int dfs(int[][] grid, int i, int j, int row, int col) {

        if (i < 0 || j >= row || j >= col || isValueZero(grid, i, j, row)) {
            return 0;
        }

        int temp = grid[i][j];
        grid[i][j] = 0;

        int upSum = dfs(grid, i-1, j, row, col);
        int downSum = dfs(grid, i+1, j, row, col);
        int leftSum = dfs(grid, i, j-1, row, col);
        int rightSum = dfs(grid, i, j+1, row, col);

        grid[i][j] = temp;

        return temp + Math.max(Math.max(Math.max(upSum, downSum), leftSum), rightSum);
    }

    private boolean isValueZero(int[][] grid, int i, int j, int row) {

        if (i < row && j >= 0){
            return grid [i][j] == 0;
        }

        return true;
    }

    public int[] dailyTemperatures(int[] temperatures) {

        int[] res =  new int[temperatures.length];
        Stack<Integer> st = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--){

            while(!st.isEmpty()&& temperatures[i]>= temperatures[st.peek()])
            {
                st.pop();
            }
            if(!st.isEmpty()){
                res[i]=st.peek()-i;
            }
            st.push(i);
        }

        return res;

    }

    public int maxLevelSum(Node root) {

        Queue<Node> c = new LinkedList<>();

        c.add(root);

        int level = 0;
        int maxLevel = 0;
        int maxLevelSum = Integer.MIN_VALUE;

        while (c.size() > 0) {
            level++;
            int sum = 0;
            int currLevelSize = c.size();
            for (int i = 0; i < currLevelSize; i++){
                Node curr = c.poll();
                sum += curr.getValue();

                if(curr.getLeft() != null) {
                    c.add(curr.getLeft());
                }

                if(curr.getRight() != null) {
                    c.add(curr.getRight());
                }
            }

            if (sum > maxLevelSum) {
                maxLevel = level;
                maxLevelSum = sum;
            }
        }

        return maxLevel;
    }

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> visibleValues = new ArrayList<>();

        if (root == null) {
          return visibleValues;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++){
                TreeNode currentValue = queue.remove();

                if (i == size-1) {
                    visibleValues.add(currentValue.getVal());
                }

                if (currentValue.getLeft() != null){
                    queue.add(currentValue.getLeft());
                }

                if (currentValue.getRight() != null){
                    queue.add(currentValue.getRight());
                }
            }
        }

        return visibleValues;

    }
}
