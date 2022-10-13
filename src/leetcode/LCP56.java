package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author 86152
 * 01BFS
 */
public class LCP56 {

    int[][] shift = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    char[] move = {'>', '<', 'v', '^'};

    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        char c[][] = new char[matrix.length][];
        for (int i = 0; i < c.length; i++) {
            c[i] = matrix[i].toCharArray();
        }
        int[][] minChange = new int[c.length][c[0].length];
        for (int i = 0; i < c.length; i++) {
            Arrays.fill(minChange[i], 10005);
        }
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minChange[start[0]][start[1]] = 0;
        q.add(new int[]{start[0], start[1], 0});
        while (q.size() > 0) {
            int a[] = q.poll();
            if (minChange[a[0]][a[1]] < a[2]) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int x = a[0] + shift[i][0], y = a[1] + shift[i][1];
                if (x >= 0 && x < c.length && y >= 0 && y < c[0].length) {
                    int change = a[2];
                    if (c[a[0]][a[1]] != move[i]) {
                        change++;
                    }
                    if (minChange[x][y] > change) {
                        minChange[x][y] = change;
                        q.add(new int[]{x, y, change});
                    }
                }
            }
        }
        return minChange[end[0]][end[1]];
    }
}
