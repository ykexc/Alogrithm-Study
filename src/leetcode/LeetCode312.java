package leetcode;

/**
 * 区间DP
 */
public class LeetCode312 {

    public int maxCoins(int[] nums) {
        var n = nums.length;
        var nums1 = new int[n + 2];
        System.arraycopy(nums, 0, nums1, 1, n);
        nums1[0] = nums1[n + 1] = 1;
        var dp = new int[n + 2][n + 2];
        for (var i = n; i >= 0; i--) {
            for (var j = i + 1; j <= n + 1; j++) {
                for (var k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + nums1[i] * nums1[j] * nums1[k]);
                }
            }
        }
        return dp[0][n + 1];
    }

}
