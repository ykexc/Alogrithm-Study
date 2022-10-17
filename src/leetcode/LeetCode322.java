package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode322 {

    public int coinChange(int[] coins, int amount) {
        var dp = new int[amount + 1];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == 0x3f3f3f3f ? -1 : dp[amount];
    }

}
