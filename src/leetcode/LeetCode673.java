package leetcode;

/**
 * 序列DP,LIS问题
 */
public class LeetCode673 {



    public int findNumberOfLIS(int[] nums) {
        var n = nums.length;
        var dp = new int[n];
        var cnt = new int[n];
        var max = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        var ans = 0;
        for (int i = 0; i < n; i++) if (dp[i] == max) ans += cnt[i];
        return ans;
    }
}
