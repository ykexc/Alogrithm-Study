package leetcode;

/**
 * @author 86152
 */
public class LeetCode494 {


    int ans = 0;
    int _target;
    public int findTargetSumWays(int[] nums, int target) {
        _target = target;
        dfs(nums, 0, 0);
        return ans;
    }

    void dfs(int[] nums, int sum, int index) {
        if (index == nums.length) {
            if (sum == _target) ans++;
            return;
        }
        dfs(nums, sum + nums[index], index + 1);
        dfs(nums, sum - nums[index], index + 1);
    }

        public int findTargetSumWays2(int[] nums, int target) {
            int sum = 0;
            for(int i : nums){
                sum+= i;
            }
            if(sum < target ||(sum - target)%2!=0){return 0;}
            int x = (sum - target) / 2;
            int [][]dp = new int[nums.length + 1][x + 1];
            dp[0][0] = 1;
            for (int i = 1; i <=nums.length ; i++) {
                for (int j = 0; j <=x ; j++) {
                    dp[i][j] += dp[i - 1][j];
                    if(nums[i - 1] <= j){
                        dp[i][j] += dp[i -1][j - nums[i - 1]];
                    }
                }
            }
            return dp[nums.length][x];
        }
}
