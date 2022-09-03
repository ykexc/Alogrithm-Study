package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode646 {

    public int findLongestChain(int[][] pairs) {
        int ans = 0;
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < pairs.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = dp[j] + 1;
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        return ans;
    }

    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (pair1, pair2) -> pair1[1] - pair2[1]);
        int last = Integer.MIN_VALUE;
        int ans = 0;
        for (int[] pair : pairs) {
            if (pair[0] > last) {
                ans ++;
                last = pair[1];
            }
        }
        return ans;
    }
}
