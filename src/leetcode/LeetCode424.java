package leetcode;

import java.util.Arrays;

/**
 * 滑动窗口
 */
public class LeetCode424 {

    public int characterReplacement(String s, int k) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        int ans = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[cs[r] - 'A']++;
            while (!check(cnt, k)) cnt[cs[l++] - 'A']--;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    boolean check(int[] cnt, int k) {
        return Arrays.stream(cnt).sum() - Arrays.stream(cnt).max().getAsInt() <= k;
    }
}
