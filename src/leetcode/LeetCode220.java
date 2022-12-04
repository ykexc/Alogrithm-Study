package leetcode;

import java.util.*;

public class LeetCode220 {

    /**
     * 平衡二叉搜索树
     *
     * @param nums
     * @param i
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int i, int v) {
        var n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int j = 0; j < n; j++) {
            Long l = (long) nums[j];
            Long floor = set.floor(l);
            Long ceiling = set.ceiling(l);
            if (floor != null && l - floor <= v) return true;
            if (ceiling != null && ceiling - l <= v) return true;
            if (j >= i) set.remove((long) nums[j - i]);
            set.add((long) nums[j]);
        }
        return false;
    }

    /**
     * 桶排序
     *
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
    long size;

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        size = t + 1L;
        for (int i = 0; i < n; i++) {
            long u = (long) nums[i];
            long idx = getIdx(u);
            // 目标桶已存在（桶不为空），说明前面已有 [u - t, u + t] 范围的数字
            if (map.containsKey(idx)) return true;
            // 检查相邻的桶
            long l = idx - 1, r = idx + 1;
            if (map.containsKey(l) && u - map.get(l) <= t) return true;
            if (map.containsKey(r) && map.get(r) - u <= t) return true;
            // 建立目标桶
            map.put(idx, u);
            // 移除下标范围不在 [max(0, i - k), i) 内的桶
            if (i >= k) map.remove(getIdx((long) nums[i - k]));
        }
        return false;
    }

    long getIdx(long u) {
        return u >= 0 ? u / size : ((u + 1) / size) - 1;
    }

}
