package luogu;

import java.util.Scanner;

/**
 * @author 86152
 */
public class P2689 {

    static int[] index = new int[51];
    static int min = 0x3f3f3f3f;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int sx, sy, tx, ty, n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sx = sc.nextInt();
        sy = sc.nextInt();
        tx = sc.nextInt();
        ty = sc.nextInt();
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            char c = sc.next().charAt(0);
            if (c == 'E') {
                index[i] = 4;
            } else if (c == 'W') {
                index[i] = 3;
            } else if (c == 'N') {
                index[i] = 1;
            } else {
                index[i] = 2;
            }
        }
        dfs(sx, sy,1, 0);
        if (min == 0x3f3f3f3f) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
    public static void dfs(int x, int y, int in, int ans) {
        if (x == tx && y == ty) {
            min = Math.min(min, ans);
            return;
        }
        if (in > n){return;}
        dfs(x, y, in + 1, ans);
        dfs(x + dx[index[in]], y + dy[index[in]], in + 1, ans + 1);
    }
}
