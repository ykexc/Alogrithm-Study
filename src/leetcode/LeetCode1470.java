package leetcode;

/**
 * @author 86152
 */
public class LeetCode1470 {
    public int[] shuffle(int[] nums, int n) {
        for (int i = 0; i < 2 * n; i ++) {
            int j = i < n ? 2 * i : 2 * i - 2 * n + 1;
            nums[j] |= ((nums[i] % 1024) << 10);
        }
        for(int i = 0; i < nums.length; i ++) {
            nums[i] >>= 10;
        }
        return nums;
    }
}
