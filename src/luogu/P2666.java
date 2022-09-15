package luogu;

import java.util.Scanner;

/**
 * @author 86152
 */
public class P2666 {
    public static void main(String[] args) {
        int ans = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i <= 100; ++i)
            for (int j = 0; j <= 100; ++j)
                for (int k = 0; k <= 100; ++k)
                    for (int m = 0; m <= 100; ++m)
                        if (i * i + j * j + k * k + m * m == n) ans++;
        System.out.println(ans);
    }
}
