package leetcode;


/**
 * 四维线性DP,暂时只会四维
 */
public class LeetCode741 {

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][][] dp = new int[n + 1][n + 1][n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    for (int l = 0; l < n + 1; l++) {
                        dp[i][j][k][l] = Integer.MIN_VALUE;
                    }
                }
            }
        }

        dp[0][1][0][1] = 0;
        dp[1][0][1][0] = 0;
        dp[0][1][1][0] = 0;
        dp[1][0][0][1] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    for (int l = 1; l <= n; l++) {
                        if (grid[i - 1][j - 1] == -1 || grid[k - 1][l - 1] == -1) {
                            dp[i][j][k][l] = Integer.MIN_VALUE;
                        } else {
                            dp[i][j][k][l] = Math.max(dp[i][j][k][l], dp[i - 1][j][k - 1][l]);
                            dp[i][j][k][l] = Math.max(dp[i][j][k][l], dp[i][j - 1][k - 1][l]);
                            dp[i][j][k][l] = Math.max(dp[i][j][k][l], dp[i - 1][j][k][l - 1]);
                            dp[i][j][k][l] = Math.max(dp[i][j][k][l], dp[i][j - 1][k][l - 1]);

                            if (i == k && j == l) {
                                dp[i][j][k][l] += grid[k - 1][l - 1];
                            } else {
                                dp[i][j][k][l] += grid[k - 1][l - 1];
                                dp[i][j][k][l] += grid[i - 1][j - 1];
                            }
                        }
                    }
                }
            }
        }


        return Math.max(dp[n][n][n][n], 0);
    }

    /**
     * 三维DP
     * @param grid
     * @return
     */

    static int[][][] dp;
    public int cherryPickup2(int[][] grid) {
        int n = grid.length;
        dp = new int[n * 2 + 2][n + 1][n + 1];
        for (int i = 0; i <= 2 * n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        dp[2][1][1] = grid[0][0];
        for (int i = 3; i <= 2 * n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    int c1 = i - j, c2 = i - k;
                    if (c1 <= 0 || c1 > n || c2 <= 0 || c2 > n) continue;
                    if (grid[j - 1][c1 - 1] == -1 || grid[k - 1][c2 - 1] == -1) continue;
                    dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i - 1][j - 1][k], Math.max(dp[i - 1][j - 1][k - 1], dp[i - 1][j][k - 1])));
                    dp[i][j][k] += grid[j - 1][c1 - 1];
                    if (j != k || c1 != c2) {
                        dp[i][j][k] += grid[k - 1][c2 - 1];
                    }
                }
            }
        }
        return dp[2 * n][n][n];
    }
}
