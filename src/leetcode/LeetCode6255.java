package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * dfs,bfs,并查集
 */
public class LeetCode6255 {


    int[] union;

    int find(int i) {
        if (union[i] == i) return i;
        return union[i] = find(union[i]);
    }

    /**
     * 并查集解法
     *
     * @param n
     * @param roads
     * @return
     */
    public int minScore(int n, int[][] roads) {
        union = new int[n + 1];
        for (int i = 0; i <= n; i++) union[i] = i;
        for (var road : roads) {
            var a = find(road[0]);
            var b = find(road[1]);
            union[b] = a;
        }
        var ans = Integer.MAX_VALUE;
        for (var road : roads) {
            if (find(road[0]) == find(n)) {
                ans = Math.min(ans, road[2]);
            }
        }
        return ans;
    }

    List<int[]>[] map;
    boolean[] vis;

    int ans = Integer.MAX_VALUE;
    public int minScore2(int n, int[][] roads) {
        map = new List[n + 1];
        vis = new boolean[n + 1];
        for (int i = 0; i <= n; i++) map[i] = new ArrayList<>();
        for (var road : roads) {
            var x = road[0];
            var y = road[1];
            var dis = road[2];
            map[x].add(new int[]{y, dis});
            map[y].add(new int[]{x, dis});
        }
        dfs(1);
        return ans;
    }

    void dfs(int i) {
        if (!vis[i]) {
            vis[i] = true;
            for (var t : map[i]) {
                ans = Math.min(t[1], ans);
                if (!vis[t[0]]) {
                    dfs(t[0]);
                }
            }
        }
    }

    void bfs() {
        var queue = new ArrayDeque<Integer>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int t = queue.pollFirst();
            vis[t] = true;
            for (var p : map[t]) {
                ans = Math.min(ans, p[1]);
                if (!vis[p[0]]) queue.addLast(p[0]);
            }
        }
    }
}
