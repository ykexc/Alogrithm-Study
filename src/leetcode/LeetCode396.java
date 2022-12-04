package leetcode;

/**
 * 数学
 */
public class LeetCode396 {

    public int maxRotateFunction(int[] nums) {
        var ans = 0;
        var sum = 0;
        var n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += i * nums[i];
        }
        int pre = ans;
        for (int i = 1, t = ans; i < nums.length; i++) {
            t = pre + sum - n * nums[n - i];
            ans = Math.max(ans, t);
            pre = t;
        }
        return ans;
    }

}
