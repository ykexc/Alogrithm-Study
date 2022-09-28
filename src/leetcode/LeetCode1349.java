package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode1349 {
    public int maxStudents(char[][] seats) {
        int[] valid = new int[seats.length];
        int m = seats.length;
        int n = seats[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valid[i] <<= 1;
                valid[i] |= (seats[i][j] == '.' ? 1 : 0);
            }
        }
        int ans = 0;
        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                if ((j & valid[i]) == j && (j & j << 1) == 0) {
                    if (i == 0) dp[i][j] = Integer.bitCount(j);
                    else {
                        for (int k = 0; k < 1 << n; k++) {
                            if (dp[i - 1][k] != -1 && (j & k >> 1) == 0 && (k & j >> 1) == 0)
                                dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Integer.bitCount(j));
                        }
                    }
                }
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
    }
}
