package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * bfs, A*
 */

public class LeetCode675 {

    int[][] map = new int[50][50];
    int n, m;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {
        n = forest.size();
        m = forest.get(0).size();
        List<int[]> lis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = forest.get(i).get(j);
                map[i][j] = x;
                if (x != 0 && x != 1) lis.add(new int[]{i, j, x});
            }
        }
        int ans = 0;
        int x = 0, y = 0;
        lis.sort((a, b) -> a[2] - b[2]);
        for (int[] li : lis) {
            int dx = li[0], dy = li[1];
            int t = bfs(x, y, dx, dy);
            if (t == -1) return -1;
            ans += t;
            x = dx;
            y = dy;
        }
        return ans;
    }
    int bfs(int sx, int sy, int ex, int ey) {
        var queue = new ArrayDeque<int[]>();
        boolean[][] vis = new boolean[50][50];
        queue.addLast(new int[] {sx, sy, 0});
        vis[sx][sy] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.pollFirst();
            int x = poll[0], y = poll[1], v = poll[2];
            if (x == ex && y == ey) return v;
            for (int[] dir : dirs) {
                int xx = x + dir[0], yy = y + dir[1], vv = v + 1;
                if (xx >= 0 && xx < n && yy >= 0 && yy < m && !vis[xx][yy] && map[xx][yy] != 0) {
                    vis[xx][yy] = true;
                    queue.addLast(new int[] {xx, yy, vv});
                }
            }
        }
        return -1;
    }

}
