package codeforces;

/**
 * @author 86152
 * Color the Picture
 */

public class CF1710 {
    static In in = new FastIn();
    static Out out = new Out(false);
    static final long inf = 0x1fffffffffffffffL;
    static final int iinf = 0x3fffffff;
    static final double eps = 1e-9;
    static long mod = 998244353;

    void solve() {
        int t = in.nextInt();

        while (t-- > 0) {
            int row = in.nextInt();
            int col = in.nextInt();
            int k = in.nextInt();
            int[] a = in.nextIntArray(k);
            out.println(check(row, col, a) || check(col, row, a) ? "Yes" : "No");
        }
    }

    private boolean check(int row, int col, int[] a) {
        boolean f = false; long sum = 0;
        for (int i = 0; i < a.length; i++) {
            int c = a[i] / row;
            if (c >= 3) {
                f = true;
            }
            if (c >= 2) {
                sum += c;
            }
        }
        return sum >= col && (col % 2 == 0 || f);
    }


    public static void main(String... args) {
        new CF1710().solve();
        out.flush();
    }
}







