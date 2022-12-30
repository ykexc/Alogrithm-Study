package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode417 {


    List<List<Integer>> ans = new ArrayList<>();
    int[][] _heights;
    int n, m;
    boolean[][] pa;
    boolean[][] atl;
    boolean[][] vis;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        n = heights.length;
        m = heights[0].length;
        pa = new boolean[n][m];
        atl = new boolean[n][m];
        vis = new boolean[n][m];
        _heights = heights;
        boolean f1, f2 = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                vis[i][j] = true;
                f1 = dfs1(i, j);
                vis = new boolean[n][m];
                if (f1) {
                    vis[i][j] = true;
                    f2 = dfs2(i, j);
                    vis = new boolean[n][m];
                }
                if (f1 && f2) {
                    List<Integer> lis = new ArrayList<>();
                    lis.add(i);
                    lis.add(j);
                    ans.add(lis);
                }
            }
        }
        return ans;

    }

    boolean dfs1(int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (x == 0 || y == 0) {
                return true;
            }
            for (int[] dir : dirs) {
                int xx = x + dir[0], yy = y + dir[1];
                if (xx >= 0 && xx < n && yy >= 0 && yy < m && !vis[xx][yy] && _heights[xx][yy] <= _heights[x][y]) {
                    vis[xx][yy] = true;
                    queue.add(new int[]{xx, yy});
                }
            }
        }
        return false;
    }

    boolean dfs2(int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (x == n - 1 || y == m - 1) {
                return true;
            }
            for (int[] dir : dirs) {
                int xx = x + dir[0], yy = y + dir[1];
                if (xx >= 0 && xx < n && yy >= 0 && yy < m && !vis[xx][yy] && _heights[xx][yy] <= _heights[x][y]) {
                    vis[xx][yy] = true;
                    queue.add(new int[]{xx, yy});
                }
            }
        }
        return false;

    }
}
