package leetcode;

import java.util.Arrays;

/**
 * 线性DP,记忆化搜索
 */
public class LeetCode1463 {

    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // dp[x1][y1][x2][y2]表示机器人从(x1, y1)(x2, y2)出发到达底行的最大得分
        // 观察到x1 == x2 始终成立，降维——dp[x][y1][y1]
        int[][][] dp = new int[m][n][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        //起始位置
        dp[0][0][n - 1] = grid[0][0] + grid[0][n - 1];

        for (int x = 1; x < m; x++) {
            for (int y1 = 0; y1 < n; y1++) {
                for (int y2 = n - 1; y2 >= 0; y2--) {
                    //上+上
                    dp[x][y1][y2] = dp[x - 1][y1][y2];
                    //左+左
                    if (y1 > 0) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1 - 1][y2 - 1]);
                    //右+右
                    if (y2 < n - 1) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1 + 1][y2 + 1]);
                    //左+右
                    if (y1 > 0 && y2 < n - 1) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1 - 1][y2 + 1]);
                    //右+左
                    if (y1 < n - 1 && y2 > 0) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1 + 1][y2 - 1]);

                    //上+左
                    if (y2 > 0) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1][y2 - 1]);

                    // 上+右
                    if (y2 < n - 1) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1][y2 + 1]);

                    //左+上
                    if (y1 > 0) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1 - 1][y2]);

                    //右+上
                    if (y1 < n - 1) dp[x][y1][y2] = Math.max(dp[x][y1][y2], dp[x - 1][y1 + 1][y2]);

                    //加上当前层的得分
                    if (y1 == y2) dp[x][y1][y2] += grid[x][y1];
                    else dp[x][y1][y2] += (grid[x][y1] + grid[x][y2]);
                }
            }
        }
//寻找最大值
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp[m - 1][i][j]);
            }
        }
        return res;

    }

    int m;
    int n;
    int[] dirs = { -1, 0, 1 };

    public int cherryPickup2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][][] memo = new int[m][n][n];
        for (int[][] a : memo) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(grid, 0, 0, n - 1, memo);
    }

    private int dfs(int[][] grid, int i, int j1, int j2, int[][][] memo) {
        if (i == m || j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || j1 == j2) return 0;//关键j1 == j2的情况一定会有其他情况把它覆盖
        if (memo[i][j1][j2] != -1) return memo[i][j1][j2];
        int sum = 0;
        for (int x : dirs) {
            for (int y : dirs) {
                sum = Math.max(sum, dfs(grid, i + 1, j1 + x, j2 + y, memo));
            }
        }
        return memo[i][j1][j2] = grid[i][j1] + grid[i][j2] + sum;
    }

}
