package leetcode;

import java.util.Arrays;

/**
 * '数位DP，数学排列组合
 */

public class LeetCode357 {
    static int[] dp = new int[10];

    /**
     * 排列组合
     */
    static {
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= 9; i++) {
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - i + 1);
        }
    }

    public int countNumbersWithUniqueDigits1(int n) {
        return dp[n];
    }

    /**
     * 数位DP
     */
    int[] fx;

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        fx = new int[n + 1];
        var record = new int[10];
        Arrays.fill(fx, -1);
        return f(0, false, record);
    }

    int f(int i, boolean isNum, int[] record) {
        if (i == fx.length) return 1;
        if (isNum && fx[i] != -1) return fx[i];
        var res = 0;
        if (!isNum) {
            res += f(i + 1, false, record);
        }
        for (int j = isNum ? 0 : 1; j <= 9; j++) {
            if (record[j] == 1) continue;
            int[] clone = record.clone();
            clone[j] = 1;
            res += f(i + 1, true, clone);
        }
        if (isNum) {
            fx[i] = res;
        }
        return res;
    }

}
