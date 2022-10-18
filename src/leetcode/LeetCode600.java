package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode600 {

    char[] s;
    int[][] dp;

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        dp = new int[s.length][2];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f(0, false, true);
    }

    private int f(int i, boolean pre, boolean isLimit) {
        if (i == s.length) return 1;
        if (!isLimit && dp[i][pre ? 1 : 0] >= 0) return dp[i][pre ? 1 : 0];
        int up = isLimit ? s[i] - '0' : 1;
        var res = f(i + 1, false, isLimit && up == 0);
        if (!pre && up == 1) res += f(i + 1, true, isLimit);
        if (!isLimit) dp[i][pre ? 1 : 0] = res;
        return res;
    }
}
