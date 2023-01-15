package leetcode;

public class LeetCode132 {

    public int minCut(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        // g[l][r] 代表 [l,r] 这一段是否为回文串
        boolean[][] g = new boolean[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                if (len == 1) {
                    g[l][r] = true;
                } else {
                    if (cs[l - 1] == cs[r - 1]) {
                        if (r - l == 1 || g[l + 1][r - 1]) {
                            g[l][r] = true;
                        }
                    }
                }
            }
        }

        // f[r] 代表将 [1,r] 这一段分割成若干回文子串所需要的最小分割次数
        int[] f = new int[n + 1];
        for (int r = 1; r <= n; r++) {
            // 如果 [1,r] 满足回文，不需要分割
            if (g[1][r]) {
                f[r] = 0;
            } else {
                // 先设定一个最大分割次数（r 个字符最多消耗 r - 1 次分割）
                f[r] = r - 1;
                // 在所有符合 [l,r] 回文的方案中取最小值
                for (int l = 1; l <= r; l++) {
                    if (g[l][r]) f[r] = Math.min(f[r], f[l - 1] + 1);
                }
            }
        }

        return f[n];
    }



}
