package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode233 {

    int[][] dp;
    char[] s;
    public int countDigitOne(int n) {
        s = String.valueOf(n).toCharArray();
        dp = new int[s.length][s.length];
        for (int i = 0; i < s.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f(0, 0, true);
    }
    private int f(int i, int cnt, boolean isLimit) {
        if (i == s.length) return cnt;
        if (!isLimit && dp[i][cnt] >= 0) return dp[i][cnt];
        int res = 0;
        int up = isLimit ? s[i] - '0' : 9;
        for (int j = 0; j <= up; j++) {
            res += f(i + 1, cnt + (j == 1 ? 1 : 0), isLimit && j == up);
        }
        if (!isLimit) dp[i][cnt] = res;
        return res;
    }

}
