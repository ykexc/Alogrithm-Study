package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 86152
 */
public class LeetCode15 {

    public List<List<Integer>> threeSum(int[] nums) {
        var ans = new ArrayList<List<Integer>>();

        Arrays.sort(nums);
        if (nums[0] > 0) return ans;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    while (nums[l] == nums[l + 1]) l++;
                    while (nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                } else l++;
            }
        }
        return ans;
    }

}
