package leetcode;

import java.util.*;

/**
 * 字符串哈希,序列DP
 */
public class LeetCode472 {

    Set<Long> set = new HashSet<>();
    static final int P = 131, OFFSET = 128;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        var ans = new ArrayList<String>();
        for (var s : words) {
            long hash = 0;
            for (char c : s.toCharArray()) hash = hash * P + (c - 'a') + OFFSET;
            set.add(hash);
        }
        for (var word : words) {
            if (check(word)) ans.add(word);
        }
        return ans;
    }

    boolean check(String s) {
        var n = s.length();
        char[] chars = s.toCharArray();
        var dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (dp[i] == -1) continue;
            long cur = 0;
            for (int j = i + 1; j <= n; j++) {
                cur = cur * P + (chars[j - 1] - 'a') + OFFSET;
                if (set.contains(cur)) dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }
        return dp[n] > 1;
    }

}
