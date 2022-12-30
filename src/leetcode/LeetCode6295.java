package leetcode;

/**
 * 容斥原理
 */
public class LeetCode6295 {

    public int minimizeSet(int d1, int d2, int u1, int u2) {
        long l = 1, r = (u1 + u2) * 2L;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(d1, d2, u1, u2, mid)) {
                r = mid;
            } else l = mid + 1;
        }
        return (int) l;
    }

    boolean check(long d1, long d2, int u1, int u2, long x) {
        long lcm = lcm(d1, d2);
        return x - x / d1 >= u1 && x - x / d2 >= u2 && x - x / lcm >= u1 + u2;
    }


    long gcd(long a, long b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }


}
