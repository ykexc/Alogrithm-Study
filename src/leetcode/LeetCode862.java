package leetcode;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.TreeMap;

public class LeetCode862 {

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length, ans = Integer.MAX_VALUE;
        long sum = 0;
        TreeMap<Long, Integer> map = new TreeMap<>();
        map.put(0L, 0);
        for (int i = 1; i <= n; i++) {
            sum += nums[i - 1];
            Map.Entry<Long, Integer> it;
            while ((it = map.floorEntry(sum - k)) != null) {
                ans = Math.min(ans, i - it.getValue());
                map.remove(it.getKey());
            }
            // 即使覆盖了也没关系, 因为 x 越大，y - x 就会越小
            map.put(sum, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int shortestSubarray2(int[] nums, int k) {
        int n = nums.length, ans = n + 1;
        var s = new long[n + 1];
        for (var i = 0; i < n; ++i)
            s[i + 1] = s[i] + nums[i]; // 计算前缀和
        var q = new ArrayDeque<Integer>();
        for (var i = 0; i <= n; ++i) {
            var curS = s[i];
            while (!q.isEmpty() && curS - s[q.peekFirst()] >= k)
                ans = Math.min(ans, i - q.pollFirst()); // 优化一
            while (!q.isEmpty() && s[q.peekLast()] >= curS)
                q.pollLast(); // 优化二
            q.addLast(i);
        }
        return ans > n ? -1 : ans;
    }
}
