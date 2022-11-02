package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode797 {

    List<List<Integer>> ans;
    List<Integer>[] map;
    int n;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        n = graph.length;
        ans = new ArrayList<>();
        map = new List[n];
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
            for (int j = 0; j < graph[i].length; j++) {
                map[i].add(graph[i][j]);
            }
        }
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        dfs(0, temp);
        return ans;
    }

    void dfs(int index, List<Integer> list) {
        if (index == n - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (Integer i : map[index]) {
                list.add(i);
                dfs(i, list);
                list.remove(i);
        }
    }

}
