package leetcode;

import java.util.HashSet;

public class LeetCode805 {

    public boolean splitArraySameAverage(int[] nums) {
        var n = nums.length;
        if (n == 1) return false;
        var sum = 0;
        for (int num : nums) sum += num;
        var arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i] * n - sum;
        var m = n / 2;
        var set = new HashSet<Integer>();
        for (int i = 1; i < 1 << m; i++) {
            int ts = 0;
            for (int j = 0; j < m; j++) {
                if ((i >> j & 1) == 1) {
                    ts += arr[j];
                }
            }
            if (ts == 0) return true;
            set.add(ts);
        }
        var sum2 = 0;
        for (int i = m; i < n; i++) sum2 += arr[i];
        for (int i = 1; i < 1 << (n - m); i++) {
            int ts = 0;
            for (int j = m; j < n; j++) {
                if ((i >> (j - m) & 1) == 1) {
                    ts += arr[j];
                }
            }
            if (ts == 0 || (set.contains(-ts) && ts != sum2)) return true;
        }
        return false;
    }

}
