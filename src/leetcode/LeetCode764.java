package leetcode;

import java.util.Arrays;

public class LeetCode764 {

    public int orderOfLargestPlusSign(int n, int[][] mines) {

        var grid = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(grid[i], 1);
        for (int[] mine : mines) grid[mine[0]][mine[1]] = 0;
        var up = new int[n][n];
        var down = new int[n][n];
        var left = new int[n][n];
        var right = new int[n][n];
        System.arraycopy(grid[0], 0, up[0], 0, n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                up[i][j] = grid[i][j] == 1 ? grid[i][j] + up[i - 1][j] : 0;
            }
        }
        System.arraycopy(grid[n - 1], 0, down[n - 1], 0, n);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                down[i][j] = grid[i][j] == 1 ? down[i + 1][j] + grid[i][j] : 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    left[i][j] = grid[i][j];
                } else left[i][j] = grid[i][j] == 1 ? grid[i][j] + left[i][j - 1] : 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (j == n - 1) {
                    right[i][j] = grid[i][j];
                } else right[i][j] = grid[i][j] == 1 ? grid[i][j] + right[i][j + 1] : 0;
            }
        }
        var ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (up[i][j] >= 1 && down[i][j] >= 1 && left[i][j] >= 1 && right[i][j] >= 1) {
                    int temp = Math.min(up[i][j], Math.min(down[i][j], Math.min(left[i][j], right[i][j])));
                    ans = Math.max(ans, temp);
                }
            }
        }
        return ans;
    }
}
