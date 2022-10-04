package leetcode;

/**
 * @author 86152
 */
public class LeetCode581 {

    public int findUnsortedSubarray(int[] nums) {
        var index = -1;
        var stack = new int[nums.length];
        var l = nums.length;
        var r = -1;
        for (int i = 0; i < nums.length; i++) {
            while (index != -1 && nums[stack[index]] > nums[i]) {
                l = Math.min(l, stack[index--]);
            }
            stack[++index] = i;
        }
        index = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (index != -1 && nums[stack[index]] < nums[i]) {
                r = Math.max(r, stack[index--]);
            }
            stack[++index] = i;
        }
        return l < r ? r - l + 1 : 0;
    }

}
