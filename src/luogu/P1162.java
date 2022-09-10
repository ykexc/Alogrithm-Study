package luogu;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86152
 */
public class P1162 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map = new int[35][35];
    static boolean[][] used = new boolean[35][35];

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        Read sc = new Read();
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        Deque<Point> deque = new ArrayDeque<>();
        used[0][0] = true;
        deque.addLast(new Point(0, 0));
        while (!deque.isEmpty()) {
            Point temp = deque.pollFirst();
            for (int i = 0; i < 4; i++) {
                int x = temp.x + dx[i];
                int y = temp.y + dy[i];
                if (x < 0 || y < 0 || x > n + 1 || y > n + 1 || used[x][y] || map[x][y] == 1) {
                    continue;
                }
                used[x][y] = true;
                deque.addLast(new Point(x, y));
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 1) {
                    System.out.print(1 + " ");
                }
                if (map[i][j] == 0 && used[i][j]) {
                    System.out.print(map[i][j] + " ");
                }
                if (map[i][j] == 0 && !used[i][j]) {
                    System.out.print(2 + " ");
                }
            }
            System.out.println();
        }
    }
}
