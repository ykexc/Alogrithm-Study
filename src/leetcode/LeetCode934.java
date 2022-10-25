package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode934 {

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] grid;
    int n, m;
    Deque<int[]> deque = new ArrayDeque<>();

    public int shortestBridge(int[][] grid) {
        var ans = 0;
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        var flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (flag) break;
                if (grid[i][j] == 1) {
                    bfs1(i, j);
                    flag = true;
                    break;
                }
            }
        }
        while (!deque.isEmpty()) {
            ans++;
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                int[] temp = deque.pollFirst();
                for (int[] dir : dirs) {
                    assert temp != null;
                    int tx = temp[0] + dir[0];
                    int ty = temp[1] + dir[1];
                    if (isLegal(tx, ty) || grid[tx][ty] == 2 || grid[tx][ty] == 3) continue;
                    if (grid[tx][ty] == 1) return ans;
                    grid[tx][ty] = 3;
                    deque.addLast(new int[] {tx, ty});
                }
            }
        }
        return ans;
    }

    void bfs1(int x, int y) {
        Deque<int[]> deque1 = new ArrayDeque<>();
        deque1.addLast(new int[]{x, y});
        grid[x][y] = 2;
        while (!deque1.isEmpty()) {
            int[] temp = deque1.pollFirst();
            for (int[] dir : dirs) {
                int tx = temp[0] + dir[0];
                int ty = temp[1] + dir[1];
                if (isLegal(tx, ty) || grid[tx][ty] == 2 || grid[tx][ty] == 3) continue;
                if (grid[tx][ty] == 0) {
                    grid[tx][ty] = 3;
                    deque.addLast(new int[]{tx, ty});
                    continue;
                }
                grid[tx][ty] = 2;
                deque1.addLast(new int[]{tx, ty});
            }
        }
    }

    boolean isLegal(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

}
