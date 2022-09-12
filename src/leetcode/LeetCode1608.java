package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode1608 {

    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, left = 1, right = nums.length;
        while (left <= right) {
            int mid = left + right >> 1;
            if (nums[n - mid] >= mid) {
                if (mid == n || nums[n - mid - 1] < mid) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
