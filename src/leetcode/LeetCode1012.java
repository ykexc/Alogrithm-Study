package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 *数位DP
 */
public class LeetCode1012 {

    char[] s;
    int[][] dp;

    public int numDupDigitsAtMostN(int n) {
        s = String.valueOf(n).toCharArray();
        var m = s.length;
        dp = new int[m][1 << 10];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return n - f(0, 0, true, false);
    }

    private int f(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == s.length) return isNum ? 1 : 0;
        if (!isLimit && isNum && dp[i][mask] >= 0) return dp[i][mask];
        var res = 0;
        if (!isNum) res = f(i + 1, mask, false, false);
        for (int j = isNum ? 0 : 1, up = isLimit ? s[i] - '0' : 9; j <= up; j++) {
            if ((mask >> j & 1) == 0) //d不在mask中
                res += f(i + 1, mask | (1 << j), isLimit && j == up, true);
        }
        if (!isLimit && isNum) dp[i][mask] = res;
        return res;
    }

}
