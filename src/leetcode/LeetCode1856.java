package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode1856 {
    int mod = (int) 1e9 + 7;

    public int maxSumMinProduct(int[] nums) {
        var l = new int[nums.length];
        var r = new int[nums.length];
        Arrays.fill(l, -1);
        Arrays.fill(r, nums.length);
        var stack = new ArrayDeque<Integer>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i]) {
                r[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i]) {
                l[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        var sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, (sum[r[i]] - sum[l[i] + 1]) * nums[i]);
        }
        return (int) (ans % mod);
    }


}
