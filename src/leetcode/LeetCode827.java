package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 86152
 */
public class LeetCode827 {
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int N = 510;
    static int[] p = new int[N * N];
    int[] sz = new int[N * N];

    int find(int n) {
        if (p[n] == n) {
            return n;
        }
        return p[n] = find(p[n]);
    }

    void union(int a, int b) {
        int aa = find(a);
        int bb = find(b);
        if (aa == bb) {
            return;
        }
        if (sz[aa] > sz[bb]) {
            union(b, a);
        } else {
            sz[bb] += sz[aa];
            p[aa] = bb;
        }
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        for (int i = 1; i <= n * n; i++) {
            p[i] = i;
            sz[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x < 0 || y < 0 || x >= n || y >= n || grid[x][y] == 0) {
                        continue;
                    }
                    union(i * n + j + 1, x * n + y + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, sz[find(i * n + j + 1)]);
                } else {
                    int tal = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || y < 0 || x >= n || y >= n || grid[x][y] == 0) {
                            continue;
                        }
                        int s = find(x * n + y + 1);
                        if (set.contains(s)) {
                            continue;
                        }
                        tal += sz[s];
                        set.add(s);
                    }
                    ans = Math.max(ans, tal);
                }
            }
        }
        return ans;
    }


}
