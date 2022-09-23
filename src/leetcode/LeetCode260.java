package leetcode;

/**
 * @author 86152
 */
public class LeetCode260 {


    public int[] singleNumber(int[] nums) {
        var t = 0;
        for (int num : nums) t ^= num;
        int bit = t & (-t);
        int ans1 = 0;
        int ans2 = 0;
        for (int num : nums) {
            if ((bit & num) == 0) ans1 ^= num;
            else ans2 ^= num;
        }
        return new int[]{ans1, ans2};
    }

}
