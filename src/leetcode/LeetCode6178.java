package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author 86152
 */
public class LeetCode6178 {

    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        var queue = new PriorityQueue<Integer>();
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            if (queue.isEmpty()) {
                queue.add(right);
            } else {
                if (left <= queue.peek()) {
                    queue.add(right);
                } else {
                    queue.poll();
                    queue.add(right);
                }
            }
        }
        return queue.size();
    }

    public int minGroups2(int[][] intervals) {
        int MAX = (int) 1e6 + 5;
        int[] sum = new int[MAX];
        for (int[] interval : intervals) {
            sum[interval[0]]++;
            sum[interval[1] + 1]--;
        }
        int ans = sum[0];
        for (int i = 1; i < MAX; i++) {
            sum[i] += sum[i - 1];
            ans = Math.max(ans, sum[i]);
        }
        return ans;
    }
}
