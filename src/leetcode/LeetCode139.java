package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 序列DP
 */
public class LeetCode139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        var n = s.length();
        var dp = new boolean[n + 10];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (set.contains(s.substring(j, i))) dp[i] |= dp[j - 1];
            }
        }
        return dp[n];
    }

    /**
     * 记忆化搜索
     */
    int[] cache;
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        cache = new int[s.length() + 1];
        Arrays.fill(cache, -1);
        return dfs(s, 0, set);
    }
    boolean dfs(String s, int start, Set<String> set) {
        if (start == s.length()) return true;
        if (cache[start] != -1) return cache[start] == 1;
        for (int i = start + 1; i <= s.length(); i++) {
            if (!set.contains(s.substring(start, i))) continue;
            if (dfs(s, i, set)) {
                cache[i] = 1;
                return true;
            }
        }
        cache[start] = 0;
        return false;
    }

}
