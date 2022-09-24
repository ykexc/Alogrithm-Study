package leetcode;

import java.util.PriorityQueue;

/**
 * @author 86152
 */
public class LeetCode871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        var pq = new PriorityQueue<Integer>((a, b) -> b - a);
        int cur = 0, ans = 0;
        for (int idx = 0, fuel = startFuel; cur < target; ) {
            cur += fuel;
            while (idx < stations.length && stations[idx][0] <= cur) {
                pq.add(stations[idx++][1]);
            }
            if (cur < target) {
                if (pq.isEmpty()) {
                    return -1;
                }
                ans++;
                fuel = pq.poll();
            }
        }
        return ans;
    }

}
