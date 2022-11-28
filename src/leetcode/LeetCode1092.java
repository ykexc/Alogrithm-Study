package leetcode;

/**
 * LCS构造题
 */
public class LeetCode1092 {

    public String shortestCommonSupersequence(String str1, String str2) {
        var n = str1.length();
        var m = str2.length();
        str1 = " " + str1;
        str2 = " " + str2;
        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();
        var dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        var sb = new StringBuilder();
        while (n > 0 || m > 0) {
            if (n == 0) {
                sb.append(b[m--]);
            } else if (m == 0) {
                sb.append(a[n--]);
            } else if (a[n] == b[m]) {
                sb.append(a[n--]);
                m--;
            } else if (dp[n][m] == dp[n - 1][m]) {
                sb.append(a[n--]);
            } else sb.append(b[m--]);
        }
        return sb.reverse().toString();
    }

}
