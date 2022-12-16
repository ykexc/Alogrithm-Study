package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 并查集+离线做法
 */
public class LeetCode1697 {

    static int N = 100010;
    static int[] p = new int[N];

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    boolean query(int a, int b) {
        return find(a) == find(b);
    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] es, int[][] _qs) {
        for (int i = 0; i < n; i++) p[i] = i;
        int m = es.length, k = _qs.length;
        int[][] qs = new int[k][4];
        for (int i = 0; i < k; i++) qs[i] = new int[]{_qs[i][0], _qs[i][1], _qs[i][2], i};
        Arrays.sort(qs, Comparator.comparingInt(a -> a[2]));
        Arrays.sort(es, Comparator.comparingInt(a -> a[2]));
        boolean[] ans = new boolean[k];
        for (int i = 0, j = 0; i < k; i++) {
            int a = qs[i][0], b = qs[i][1], t = qs[i][2], idx = qs[i][3];
            while (j < m && es[j][2] < t) {
                union(es[j][0], es[j][1]);
                j++;
            }
            ans[idx] = query(a, b);
        }
        return ans;
    }

}
