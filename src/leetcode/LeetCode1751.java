package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 序列DP
 */
public class LeetCode1751 {

    public int maxValue(int[][] events, int k) {
        int n = events.length, m = events[0].length;
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));
        var f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            var event = events[i - 1];
            int es = event[0], en = event[1], ev = event[2];
            var last = 0;
            for (int j = i - 1; j >= 1; j--) {
                if (events[j - 1][1] < es) {
                    last = j;
                    break;
                }
            }

            //二分
            /*int last = 1, r = i - 1;
            while (last < r) {
                var mid = last + r + 1 >> 1;
                if (events[mid - 1][1] < es) last = mid;
                else r = mid - 1;
            }
            last = (r > 0 && events[r - 1][1] < es) ? r : 0;*/

            for (int j = 1; j <= k; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[last][j - 1] + ev);
            }
        }
        return f[n][k];
    }

}
