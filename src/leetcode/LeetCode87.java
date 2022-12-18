package leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 区间DP
 */
public class LeetCode87 {


    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        var n = s1.length();
        var f = new boolean[n][n][n + 1];
        for (var len = 1; len <= n; len++) {
            for (var i = 0; i + len - 1 < n; i++) {
                for (var j = 0; j + len - 1 < n; j++) {
                    if (len == 1) {
                        f[i][j][1] = (array1[i] == array2[j]);
                        continue;
                    }
                    for (var k = 1; k < len; k++) {
                        f[i][j][len] |= (f[i][j][k] && f[i + k][j + k][len - k]) || (f[i][j + len - k][k] && f[i + k][j][len - k]);
                    }
                }
            }
        }
        return f[0][0][n];
    }


    /**
     * 记忆化搜索
     */
    String _s1, _s2;
    Boolean[][][] cache;

    public boolean isScramble1(String s1, String s2) {
        _s1 = s1;
        _s2 = s2;
        var n = s1.length();
        cache = new Boolean[n][n][n + 1];
        return dfs(0, 0, n);
    }

    boolean dfs(int i, int j, int len) {
        if (cache[i][j][len] != null) return cache[i][j][len];
        String str1 = _s1.substring(i, i + len), str2 = _s2.substring(j, j + len);
        if (str1.equals(str2)) return cache[i][j][len] = true;
        if (!check(str1, str2)) return cache[i][j][len] = false;
        for (int k = 1; k < len; k++) {
            if (dfs(i, j, k) && dfs(i + k, j + k, len - k)) {
                return cache[i][j][len] = true;
            }
            if (dfs(i, j + len - k, k) && dfs(i + k, j, len - k)) {
                return cache[i][j][len] = true;
            }
        }
        return cache[i][j][len] = false;
    }


    boolean check(@NotNull String s1, String s2) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        char[] _1 = s1.toCharArray();
        char[] _2 = s2.toCharArray();
        for (int i = 0; i < s1.length(); i++) {
            arr1[_1[i] - 'a']++;
            arr2[_2[i] - 'a']++;
        }
        return Arrays.equals(arr1, arr2);
    }
}
