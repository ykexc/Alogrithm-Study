package leetcode;

import java.util.Arrays;

public class LeetCode864 {


    int[][][] V;
    int keys;
    char[][] map;
    int N, M;
    int ans = 0x3f3f3f3f;
    int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestPathAllKeys(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        map = new char[N][M];
        int sx = 0, sy = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    sx = i;
                    sy = j;
                } else if (Character.isLetter(c)) {
                    keys++;
                }
                map[i][j] = c;
            }
        }
        keys /= 2;
        V = new int[N][M][1 << keys + 1];
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) Arrays.fill(V[i][j], 0x3f3f3f3f);
        dfs(sx, sy, 0, 0);
        return ans == 0x3f3f3f3f ? -1 : ans;
    }
    boolean check(int x, int y, int key) {
        if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == '#') return false;
        if ('A' <= map[x][y] && 'Z' >= map[x][y]) {
            int t = map[x][y] - 'A';
            return ((key >> t) & 1) == 1;
        }
        return true;
    }

    void dfs(int sx, int sy, int key, int step) {
        if (step >= ans) return;
        if (key == ((1 << keys) - 1)) {
            ans = step;
            return;
        }
        if (step >= V[sx][sy][key]) return;

        V[sx][sy][key] = step;
        for (int[] dir : dirs) {
            int xx = sx + dir[0];
            int yy = sy + dir[1];
            if (check(xx, yy, key)) {
                if (map[xx][yy] >= 'a' && map[xx][yy] <= 'z') {
                    int t = map[xx][yy] - 'a';
                    int newKey = key | (1 << t);
                    dfs(xx, yy, newKey, step + 1);
                } else dfs(xx, yy, key, step + 1);
            }
        }
    }
}
