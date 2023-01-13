package leetcode;

/**
 * 二分
 */
public class LeetCode1802 {

    long _maxSum;
    int _n;
    int _index;
    public int maxValue(int n, int index, int maxSum) {
        _index = index;
        _n = n;
        _maxSum = (long) maxSum;
        int l = 1, r = maxSum;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    boolean check(int k) {
        long t = 0;
        int ll = _index, rr = _n - _index - 1;
        if (k - 1 > ll) {
            t += (long) ll * (k - 1 + k - ll) / 2;
        } else {
            t += (long) (k - 1) * k / 2;
            t += (long) ll - k + 1;
        }
        if (k - 1 > rr) {
            t += (long) rr * (k - 1 + k - rr) / 2;
        } else {
            t += (long) (k - 1) * k / 2;
            t += (long) rr - k + 1;
        }
        t += k;
        return t <= _maxSum;
    }

}
