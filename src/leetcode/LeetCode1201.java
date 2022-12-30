package leetcode;

/**
 * 容斥原理
 */
public class LeetCode1201 {

    public int nthUglyNumber(int n, int a, int b, int c) {
        long l = 1, r = (long) 2e9;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(mid, a, b, c, n)) {
                r = mid;
            } else l = mid + 1;
        }
        return (int) l;
    }

    boolean check(long x, long a, long b, long c, int n) {
        return x / a + x / b + x / c - x / lcm(a, b) - x / lcm(b, c) - x / lcm(a, c)
                + x / lcm(lcm(a, b), c) >= n;
    }


    long gcd(long a, long b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }


}
