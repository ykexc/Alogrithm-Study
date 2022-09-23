package leetcode;

/**
 * @author 86152
 */
public class LeetCode137 {

    public int singleNumber(int[] nums) {
        var ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += num >> i & 1;
            }
            if (total % 3 != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

}
