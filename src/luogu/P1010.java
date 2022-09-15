package luogu;

import java.util.Scanner;

/**
 * @author 86152
 */
public class P1010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        solve(n);
    }

    private static void solve(int n) {
        for (int i = 14; i >= 0; i--) {
            if (Math.pow(2, i) <= n) {
                if (i == 1) {
                    System.out.print(2);
                } else if (i == 0) {
                    System.out.print("2(0)");
                } else {
                    System.out.print("2(");
                    solve(i);
                    System.out.print(")");
                }
                n -= Math.pow(2, i);
                if (n != 0) {
                    System.out.print("+");
                }
            }
        }
    }
}
//2(2(2+2(0))+2)+2(2(2+2(0)))+2(2(2)+2(0))+2+2(0)
//2(2(2+2(0))+2)+2(2(2+2(0)))+2(2(2)+2(0))+2+2(0)
//2(2(22(2(0))+2(0))+22(2(0)))+2(2(22(2(0))+2(0)))+2(2(22(2(0)))+2(0))+22(2(0))+2(0)