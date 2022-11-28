package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra
 */
public class LeetCode882 {

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] map = new ArrayList[n];
        Arrays.setAll(map, e -> new ArrayList<>());
        for (var edge : edges) {
            map[edge[0]].add(new int[] {edge[1], edge[2] + 1});
            map[edge[1]].add(new int[] {edge[0], edge[2] + 1});
        }
        var ans = 0;
        int[] dist = dijkstra(map);
        for (int i : dist) if (i <= maxMoves) ans ++;
        for (var e : edges) {
            int u = e[0], d = e[1], x = e[2];
            var t1 = Math.max(maxMoves - dist[u], 0);
            var t2 = Math.max(maxMoves - dist[d], 0);
            ans += Math.min(t1 + t2, x);
        }
        for (int i : dist) System.out.println(i);

        return ans;
    }

    /**
     * 朴素
     */
    int[] dijkstra(List<int[]>[] m) {
        var n = m.length;
        var dist = new int[n];
        var vis = new boolean[n];
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[0] = 0;
        for (int i = 0; i < n; i++) {
            var t = -1;
            for (int j = 0; j < n; j++) {
                if (!vis[j] && (t == -1 || dist[j] < dist[t])) t = j;
            }
            vis[t] = true;
            for (var point : m[t]) {
                dist[point[0]] = Math.min(dist[point[0]], dist[t] + point[1]);
            }
        }
        return dist;
    }

    /**
     *堆优化
     */
    private int[] dijkstra(List<int[]>[] g, int start) {
        var dist = new int[g.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        var pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int x = p[0], d = p[1];
            if (d > dist[x]) continue;
            for (var e : g[x]) {
                int y = e[0];
                int newDist = d + e[1];
                if (newDist < dist[y]) {
                    dist[y] = newDist;
                    pq.offer(new int[]{y, newDist});
                }
            }
        }
        return dist;
    }

}
