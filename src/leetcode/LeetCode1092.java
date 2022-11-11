package leetcode;

/**
 * LCS构造题
 */
public class LeetCode1092 {

    public String shortestCommonSupersequence(String str1, String str2) {
        var n = str1.length();
        var m = str2.length();
        var dp = new int[n + 1][m + 1];
        str1 = " " + str1;
        str2 = " " + str2;
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        var ans = new StringBuilder();
        int i = n, j = m;
        while (i > 0 || j > 0) {
            if (i == 0) {
                ans.append(str2.charAt(j--));
            } else if (j == 0) {
                ans.append(str1.charAt(i--));
            } else {
                if (str1.charAt(i) == str2.charAt(j)) {
                    ans.append(str1.charAt(i--));
                    j--;
                } else {
                    if (dp[i][j] == dp[i - 1][j]) {
                        ans.append(str1.charAt(i--));
                    } else {
                        ans.append(str2.charAt(j--));
                    }
                }
            }
        }
        return ans.reverse().toString();
    }

}
