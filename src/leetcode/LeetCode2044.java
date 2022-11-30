package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 状压DP
 */
public class LeetCode2044 {

    /**
     * 二进制枚举
     *
     * @param nums
     * @return null
     */
    public int countMaxOrSubsets(int[] nums) {
        var n = nums.length;
        var mask = 1 << n;
        int ans = 0;
        int max = 0;
        for (var i = 0; i < mask; i++) {
            var cur = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) cur |= nums[j];
            }
            if (cur > max) {
                max = cur;
                ans = 1;
            } else if (cur == max) {
                ans++;
            }
        }
        return ans;
    }

    static Map<Integer, Integer> map = new HashMap<>();

    static {
        for (int i = 0; i < 20; i++) map.put((1 << i), i);
    }

    /**
     * 状压DP
     *
     * @param nums
     * @return
     */
    public int countMaxOrSubsets2(int[] nums) {
        int n = nums.length, mask = 1 << n;
        int[] f = new int[mask];
        int max = 0, ans = 0;
        for (int s = 1; s < mask; s++) {
            int lowbit = (s & -s);
            int prev = s - lowbit, idx = map.get(lowbit);
            f[s] = f[prev] | nums[idx];
            if (f[s] > max) {
                max = f[s];
                ans = 1;
            } else if (f[s] == max) {
                ans++;
            }
        }
        return ans;
    }

    int ans = 0;
    int[] _nums;
    int max;

    /**
     * 爆搜
     *
     * @param nums
     * @return
     */
    public int countMaxOrSubsets3(int[] nums) {
        _nums = nums;
        for (var num : nums) max |= num;
        dfs(0, 0, 0);
        return ans;
    }

    void dfs(int state, int value, int index) {
        if (index == _nums.length || value == max) {
            if (value == max) ans += 1 << _nums.length - index;
            return;
        }
        if ((state >> index & 1) == 0) {
            dfs(state | 1 << index, value | _nums[index], index + 1);
            dfs(state, value, index + 1);
        } else dfs(state, value, index + 1);
    }

}