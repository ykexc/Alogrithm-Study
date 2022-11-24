package leetcode;


import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 单调栈,双指针
 */
public class LeetCode795 {

    //双指针
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        var ans = 0;
        var l = -1;
        var r = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) r = i;
            if (nums[i] >= left) l = i;
            ans += l - r;
        }
        return ans;
    }

    //单调栈
    //注意在判断最值的时候，两边只需一边取等就行
    public int numSubarrayBoundedMax2(int[] nums, int left, int right) {
        var n = nums.length;
        var l = new int[n + 10];
        var r = new int[n + 10];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        var stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                r[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i]) {
                l[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        var ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < left || nums[i] > right) continue;
            ans += (i - l[i]) * (r[i] - i);
        }
        return ans;
    }

}
