package luogu;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author 86152
 */
public class P1605 {
    static int N, M, T, SX, SY, FX, FY, ans;
    static int[][] maze = new int[10][10];
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    static class Position {
        int x;
        int y;
        boolean[][] used;

        Position(int x, int y, boolean[][] used) {
            this.x = x;
            this.y = y;
            this.used = used;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        T = sc.nextInt();
        SX = sc.nextInt();
        SY = sc.nextInt();
        FX = sc.nextInt();
        FY = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                maze[i][j] = 1;
            }
        }
        for (int i = 0; i < T; i++) {
            maze[sc.nextInt()][sc.nextInt()] = 0;
        }
        //maze[SX][SY] = 0;
        bfs(SX, SY);
        System.out.println(ans);
    }

    static void dfs(int x, int y) {
        if (x == FX && y == FY) {
            ans++;
            return;
        }
        for (int i = 0; i < dx.length; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (maze[xx][yy] == 1) {
                maze[xx][yy] = 0;
                dfs(xx, yy);
                maze[xx][yy] = 1;
            }
        }
    }

    static void bfs(int x, int y) {
        Deque<Position> deque = new ArrayDeque<>();
        boolean[][] start = new boolean[10][10];
        start[x][y] = true;
        deque.add(new Position(x, y, start));
        while (!deque.isEmpty()) {
            Position position = deque.pollFirst();
            if (position.x == FX && position.y == FY) {
                ans++;
            }
            for (int i = 0; i < dx.length; i++) {
                int xx = position.x + dx[i];
                int yy = position.y + dy[i];
                if (maze[xx][yy] == 1 && !position.used[xx][yy]) {
                    boolean[][] clone = new boolean[10][10];
                    for (int j = 0; j < 10; j++) {
                        System.arraycopy(position.used[j], 0, clone[j], 0, 10);
                    }
                    clone[xx][yy] = true;
                    deque.addLast(new Position(xx, yy, clone));
                }
            }
        }
    }
}
/*
                        clone[j] = Arrays.copyOf(position.used[j], 10);次慢
                        System.arraycopy(position.used[j], 0, clone[j], 0, 10); 最快
                        clone[j] = position.used[j].clone();最慢
                        手动克隆最最慢
*/
