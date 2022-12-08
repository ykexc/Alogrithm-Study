package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最小生成树
 */
public class LeetCode1584 {

    /**
     * Kruskal
     */
    public int minCostConnectPoints(int[][] points) {
        var n = points.length;
        var heap = new PriorityQueue<Edge>((e1, e2) -> e1.cost - e2.cost);
        UnionFind unionFind = new UnionFind(n);
        for (var i = 0; i < n; i++) {
            for (var j = i + 1; j < n; j++) {
                var e1 = points[i];
                var e2 = points[j];
                int cost = Math.abs(e1[0] - e2[0]) + Math.abs(e1[1] - e2[1]);
                Edge edge = new Edge(i, j, cost);
                heap.add(edge);
            }
        }
        var ans = 0;
        var k = 0;
        while (!heap.isEmpty()) {
            Edge poll = heap.poll();
            var point1 = poll.point1;
            var point2 = poll.point2;
            var cost = poll.cost;
            if (!unionFind.check(point2, point1)) {
                ans += cost;
                unionFind.union(point1, point2);
                k++;
            }
            if (k == n - 1) break;
        }
        return ans;
    }


    static class UnionFind {
        int[] union;
        int[] rank;
        int size;

        UnionFind(int size) {
            this.size = size;
            union = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                union[i] = i;
                rank[i] = i;
            }
        }

        int find(int i) {
            if (i == union[i]) return i;
            return union[i] = find(union[i]);
        }

        boolean check(int a, int b) {
            return find(a) == find(b);
        }

        void union(int a, int b) {
            int aa = find(a);
            int bb = find(b);
            if (aa != bb) {
                if (rank[aa] < rank[bb]) {
                    union[aa] = bb;
                } else if (rank[aa] > rank[bb]) {
                    union[bb] = aa;
                } else {
                    union[aa] = bb;
                    rank[bb]++;
                }
            }
        }
    }

    static class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }

    /**
     * prim
     */
    public int minCostConnectPoints2(int[][] points) {
        var n = points.length;
        var vis = new boolean[n];
        var heap = new PriorityQueue<Edge>(Comparator.comparingInt(a -> a.cost));
        var ans = 0;
        var size = n - 1;
        for (var i = 1; i < n; i++) {
            var p0 = points[0];
            var pi = points[i];
            var distance = Math.abs(p0[0] - pi[0]) + Math.abs(p0[1] - pi[1]);
            heap.add(new Edge(0, i, distance));
        }
        vis[0] = true;
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            int point2 = edge.point2;
            int cost = edge.cost;
            if (!vis[point2]) {
                ans += cost;
                size--;
                vis[point2] = true;
                for (int i = 0; i < n; i++) {
                    if (!vis[i]) {
                        var distance = Math.abs(points[point2][0] - points[i][0]) + Math.abs(points[point2][1] - points[i][1]);
                        heap.add(new Edge(point2, i, distance));
                    }
                }
            }
            if (size == 0) break;
        }
        return ans;
    }
}
