package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 拓扑排序
 */
public class LeetCode207 {

    List<Integer>[] edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; ++i) edges[i] = new ArrayList<>();
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges[info[1]].add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges[u]) {
            if (visited[v] == 0) dfs(v);
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }
}
