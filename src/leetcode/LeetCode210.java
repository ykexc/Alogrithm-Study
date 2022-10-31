package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 拓扑排序
 */
public class LeetCode210 {

    List<Integer> ans = new ArrayList<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] map = new List[numCourses];
        var r = new int[numCourses];
        var queue = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; i++) map[i] = new ArrayList<Integer>();
        for (int[] prerequisite : prerequisites) {
            int x = prerequisite[0], y = prerequisite[1];
            r[x]++;
            map[y].add(x);
        }
        for (int i = 0; i < numCourses; i++) if (r[i] == 0) dfs(i, map, r);
        System.out.println(ans.size());
        int[] ints = ans.stream().mapToInt(a -> a).toArray();
        return ans.size() == numCourses ? ints : new int[]{};
    }

    void dfs(int i, List<Integer>[] map, int[] r) {
        ans.add(i);
        for (int x : map[i]) {
            if (--r[x] == 0) {
                dfs(x, map, r);
                r[x]++;
            }
        }
    }

}
