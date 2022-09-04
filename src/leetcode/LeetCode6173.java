package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 86152
 */
public class LeetCode6173 {
    public int maximumRows(int[][] mat, int cols) {
        int n = mat[0].length;
        List<Set<Integer>> list = new ArrayList<>();
        dfs(n, cols, 0, list, new ArrayList<>());
        int res = 0;
        for (Set<Integer> set : list) {
            res = Math.max(res, max(mat, set));
        }
        return res;
    }

    private void dfs(int n, int cols, int begin, List<Set<Integer>> res, List<Integer> path) {
        if (path.size() == cols) {
            res.add(new HashSet<>(path));
            return;
        }
        for (int i = begin; i < n; i++) {
            path.add(i);
            dfs(n, cols, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }

    int max(int[][] mat, Set<Integer> cols) {
        int res = 0;
        for (int[] ints : mat) {
            boolean flag = true;
            for (int j = 0; j < mat[0].length; j++) {
                if (ints[j] == 1 && !cols.contains(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;
    }
}
