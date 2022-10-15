package leetcode;

/**
 * @author 86152
 */
public class LeetCode416 {

    public boolean canPartition(int[] nums) {
        int n = nums.length, sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            if (num > max) max = num;
        }
        if ((sum & 1) == 1 || max > sum / 2) return false; // sum % 2 == 1，sum为奇数或max大于一半
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // dp[i][0] = true;
        dp[nums[0]] = true; // dp[0][nums[0]] = true; 不会越界
        for (int i = 1; i < n; i++) {
            for (int j = target; j > 0; j--) { // 省略 else dp[j] = dp[j]
                if (nums[i] <= j) dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

}
