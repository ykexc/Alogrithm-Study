package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口
 */
public class LeetCode992 {

    Map<Integer, Integer> map;
    int _k;

    public int subarraysWithKDistinct(int[] nums, int k) {
        map = new HashMap<>();
        _k = k;
        var n = nums.length;
        var ans = 0;
        for (int l = 0, r = 0; r < n; r++) {
            if ((r - l + 1) >= k && isValid()) {
                while (isValid()) {
                    map.put(nums[l], map.getOrDefault(nums[l], 0) - 1);
                    if (map.get(nums[l]) == 0) map.remove(nums[l]);
                    l++;
                }
            } else if (!isValid())ans++;
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
        }
        return ans;
    }

    boolean isValid() {
        return map.size() != _k;
    }

}
