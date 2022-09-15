package luogu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 86152
 */
public class P1019 {
    static int n, max;
    static int[] arr = new int[21];
    static String[] str = new String[21];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            str[i] = scanner.next();
        }
        String prefix = scanner.next();
        for (int i = 0; i < n; i++) {
            if (str[i].startsWith(prefix)) {
                arr[i] = 1;
                dfs(str[i]);
                Arrays.fill(arr, 0);
            }
        }
        System.out.println(max);
    }

    static void dfs(String s) {
        for (int i = 0; i < n; i++) {
            max = Math.max(max, str[i].length());
            String ss = plus(s, str[i]);
            if ("".equals(ss) || arr[i] == 2 || ss.length() == s.length() || ss.length() == str[i].length()) {
                continue;
            }
            max = Math.max(max, ss.length());
            arr[i]++;
            dfs(ss);
            arr[i]--;
        }
    }

    static String plus(String s1, String s2) {
        int a = s1.length() - 1;
        int b = 1;
        while (a >= 0 && b <= s2.length()) {
            if (s1.substring(a, s1.length()).equals(s2.substring(0, b))) {
                return s1 + s2.substring(b, s2.length());
            }
            a--;
            b++;
        }
        return "";
    }
}
