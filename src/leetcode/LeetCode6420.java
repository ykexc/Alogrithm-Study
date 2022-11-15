package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 树形DP
 */
public class LeetCode6420 {

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], t -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], t -> new ArrayList<>()).add(edge[0]);
        }
        int[] dist = new int[amount.length];
        mostProfitablePath(Integer.MIN_VALUE, bob, -1, dist, new boolean[amount.length], map);
        return mostProfitablePath(Integer.MIN_VALUE, 0, -1, dist, amount, map);
    }

    private boolean mostProfitablePath(int i, int bob, int j, int[] dist, boolean[] visited,
                                       HashMap<Integer, ArrayList<Integer>> map) {
        if (!visited[bob]) {
            dist[bob] = bob > 0 ? dist[bob] : i;
            for (int k : map.get(bob)) {
                dist[bob] = k != j && mostProfitablePath(i + 1, k, bob, dist, visited, map) ? i : dist[bob];
            }
        }
        return dist[bob] < 0;
    }

    private int mostProfitablePath(int i, int j, int k, int[] dist, int[] amount,
                                   HashMap<Integer, ArrayList<Integer>> map) {
        int max = Integer.MIN_VALUE;
        for (int l : map.get(j)) {
            max = Math.max(max, l == k ? Integer.MIN_VALUE : mostProfitablePath(i + 1, l, j, dist, amount, map));
        }
        return (max > Integer.MIN_VALUE ? max : 0) + (i > dist[j] ? 0 : amount[j] / (i < dist[j] ? 1 : 2));
    }

}
