package leetcode;

import java.util.HashMap;

/**
 * 前缀和,哈希表型
 */
public class LeetCode2488 {

    public int countSubarrays(int[] nums, int k) {
        var n = nums.length;
        var map = new HashMap<Integer, Integer>();
        var index = 0;
        while (nums[index] != k) index++;
        map.put(0, 1);
        var c = 0;
        for (var i = index - 1; i >= 0; i--) {
            c += nums[i] > k ? 1 : -1;
            //map.put(c, map.getOrDefault(c, 0) + 1);
            map.merge(c, 1, Integer::sum);
        }
        var ans = map.get(0) + map.getOrDefault(1, 0);
        c = 0;
        for (var i = index + 1; i < n; i++) {
            c += nums[i] > k ? -1 : 1;
            ans += map.getOrDefault(c, 0) + map.getOrDefault(c + 1, 0);
        }
        return ans;
    }

}
