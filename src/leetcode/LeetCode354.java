package leetcode;

import java.util.Arrays;

/**
 * 序列DP, LIS
 */
public class LeetCode354 {

    public int maxEnvelopes(int[][] envelopes) {
        var n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        var p = new int[n][2];
        int index = -1;
        for (int[] envelope : envelopes) {
            if (index == -1 || (envelope[0] > p[index][0] && envelope[1] > p[index][1])) {
                p[++index] = new int[]{envelope[0], envelope[1]};
            } else {
                int l = 0, r = index;
                while (l < r) {
                    int mid = l + r >> 1;
                    if (p[mid][0] >= envelope[0] || p[mid][1] >= envelope[1]) {
                        r = mid;
                    } else l = mid + 1;
                }
                p[l] = new int[]{envelope[0], envelope[1]};
            }
        }
        return index + 1;
    }

    /**
     * 空间优化
     */
    public int maxEnvelopes1(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] res = new int[envelopes.length + 1];
        int len = 0;
        res[++len] = envelopes[0][1];
        for (int i = 1; i < envelopes.length; i++) {
            if (res[len] < envelopes[i][1])
                res[++len] = envelopes[i][1];
            else {
                int left = 1;
                int right = len;
                while (left < right) {
                    int mid = left + right >> 1;
                    if (res[mid] < envelopes[i][1]) left = mid + 1;
                    else right = mid;
                }
                res[left] = envelopes[i][1];
            }
        }
        return len;
    }

}
