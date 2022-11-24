package leetcode;

import java.util.Arrays;

//Dijkstra
public class LeetCode743 {

    public int networkDelayTime(int[][] times, int n, int k) {
        var len = new int[n + 1][n + 1];
        for (var i = 1; i <= n; i++)
            for (var j = 1; j <= n; j++)
                len[i][j] = i == j ? 0 : 0x3f3f3f3f;
        for (var time : times) len[time[0]][time[1]] = time[2];
        var w = new int[n + 1];
        Arrays.fill(w, 0x3f3f3f3f);
        w[k] = 0;
        var via = new boolean[n + 1];
        for (var i = 1; i <= n; i++) {
            var t = -1;
            for (var j = 1; j <= n; j++) {
                if (!via[j] && (t == -1 || w[j] < w[t])) t = j;
            }
            via[t] = true;
            for (var j = 1; j <= n; j++) {
                w[j] = Math.min(w[j], w[t] + len[t][j]);
            }
        }
        var res = 0;
        for (var i = 1; i <= n; i++) {
            res = Math.max(res, w[i]);
        }
        return res >= 0x3f3f3f3f ? -1 : res;
    }

}
