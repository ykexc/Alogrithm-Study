package luogu;

import java.util.Scanner;

/**
 * @author 86152
 */
public class P3717 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        int[][] grid = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (Math.sqrt((x - j) * (x - j) + (y - k) * (y - k)) <= r) {
                        grid[j][k] = 1;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
