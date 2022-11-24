package leetcode;

public class LeetCode878 {

    int n, a, b, c;

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int nthMagicalNumber(int _n, int _a, int _b) {
        n = _n;
        a = _a;
        b = _b;
        c = a * b / gcd(a, b);
        long l = 0, r = (long) 1e18;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(mid) >= n) r = mid;
            else l = mid + 1;
        }
        return (int) (r % 1000000007);
    }

    long check(long x) {
        return x / a + x / b - x / c;
    }
}
