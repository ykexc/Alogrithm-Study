package leetcode;


import java.util.Arrays;

/**
 * 区间DP，但是看不懂，暂时先写记忆化搜索
 */
public class LeetCode730 {

    int[][][] cache;
    static int mod = (int) 1e9 + 7;
    String _s;

    public int countPalindromicSubsequences(String s) {
        var n = s.length();
        _s = s;
        cache = new int[4][n][n];
        for (int i = 0; i < 4; i++) for (int j = 0; j < n; j++) Arrays.fill(cache[i][j], -1);
        var ans = 0L;
        for (int i = 0; i < 4; i++) {
            ans += dfs(0, s.length() - 1, (char) ('a' + i)) % mod;
        }
        ans %= mod;
        return (int) ans;
    }

    int dfs(int l, int r, char c) {
        if (l > r) return 0;
        if (_s.charAt(l) == c && l == r) return 1;

        if (cache[c - 'a'][l][r] != -1) return cache[c - 'a'][l][r];

        var cur = 0L;
        if (_s.charAt(l) == _s.charAt(r) && _s.charAt(l) == c) {
            cur += 2;
            for (int i = 0; i < 4; i++) {
                cur += dfs(l + 1, r - 1, (char) ('a' + i)) % mod;
            }
        } else {
            cur += dfs(l + 1, r, c) % mod;
            cur += dfs(l, r - 1, c) % mod;
            cur -= dfs(l + 1, r - 1, c) % mod;
        }
        cur %= mod;
        return cache[c - 'a'][l][r] = (int) cur;
    }


    /**
     * 区间DP
     */

    int MOD = (int) 1e9 + 7;

    public int countPalindromicSubsequences2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] f = new int[n][n];
        int[] L = new int[4], R = new int[4];
        Arrays.fill(L, -1);
        for (int i = n - 1; i >= 0; i--) {
            L[cs[i] - 'a'] = i;
            Arrays.fill(R, -1);
            for (int j = i; j < n; j++) {
                R[cs[j] - 'a'] = j;
                for (int k = 0; k < 4; k++) {
                    if (L[k] == -1 || R[k] == -1) continue;
                    int l = L[k], r = R[k];
                    if (l == r) f[i][j] = (f[i][j] + 1) % MOD;
                    else if (l == r - 1) f[i][j] = (f[i][j] + 2) % MOD;
                    else f[i][j] = (f[i][j] + f[l + 1][r - 1] + 2) % MOD;
                }
            }
        }
        return f[0][n - 1];
    }
}
