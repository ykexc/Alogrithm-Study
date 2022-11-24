package leetcode;

import java.util.Arrays;

/**
 * 线性DP
 */
public class LeetCode368 {
    public int findSubstringInWraproundString(String _p) {
        var n = _p.length();
        var chars = _p.toCharArray();
        var f = new int[126];
        f[chars[0]] = 1;
        var j = 1;
        for (var i = 1; i < n; i++) {
            var c1 = chars[i - 1];
            var c2 = chars[i];
            if (c2 == c1 + 1 || (c1 == 'z' && c2 == 'a')) j++;
            else j = 1;
            f[c2] = Math.max(f[c2], j);
        }
        return Arrays.stream(f).sum();
    }
}
