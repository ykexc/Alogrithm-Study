package leetcode;

import java.util.Arrays;

public class LeetCode688 {

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (double[][] arr : dp) {
            for (double[] temp : arr) {
                Arrays.fill(temp, -1);
            }
        }
        return dfs(row, column, k, n, dp);
    }


    private double dfs(int row, int column, int k, int n, double[][][] dp) {
        if (row < 0 || row >= n) return 0;
        if (column < 0 || column >= n) return 0;
        if (k == 0) return 1;


        if (dp[k][row][column] != -1) return dp[k][row][column];

        double temp = 0;
        int newrow = row - 1, newcolumn = column - 1;
        temp += dfs(newrow, newcolumn - 1, k - 1, n, dp) / 8.0;

        temp += dfs(newrow - 1, newcolumn, k - 1, n, dp) / 8.0;

        newrow = row - 1;
        newcolumn = column + 1;
        temp += dfs(newrow, newcolumn + 1, k - 1, n, dp) / 8.0;
        temp += dfs(newrow - 1, newcolumn, k - 1, n, dp) / 8.0;

        newrow = row + 1;
        newcolumn = column - 1;
        temp += dfs(newrow + 1, newcolumn, k - 1, n, dp) / 8.0;
        temp += dfs(newrow, newcolumn - 1, k - 1, n, dp) / 8.0;

        newrow = row + 1;
        newcolumn = column + 1;
        temp += dfs(newrow, newcolumn + 1, k - 1, n, dp) / 8.0;
        temp += dfs(newrow + 1, newcolumn, k - 1, n, dp) / 8.0;

        dp[k][row][column] = temp;

        return temp;
    }

}
