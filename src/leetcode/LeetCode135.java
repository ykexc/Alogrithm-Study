package leetcode;

import java.util.Arrays;

public class LeetCode135 {

    public int candy(int[] ratings) {
        var n = ratings.length;
        var left = new int[n];
        Arrays.fill(left, 1);
        for (var i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        }
        var res = left[n - 1];
        for (var i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && left[i] < left[i + 1]) left[i] = left[i + 1] + 1;
            res += left[i];
        }
        return res;
    }

}
