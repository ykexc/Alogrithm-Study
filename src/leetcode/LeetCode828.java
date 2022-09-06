package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode828 {

    public int uniqueLetterString(String s) {
        var left = new int[s.length()];
        var right = new int[s.length()];
        var index = new int[26];
        Arrays.fill(index, -1);
        for (int i = 0; i < s.length(); i++) {
            left[i] = index[s.charAt(i) - 'A'];

            index[s.charAt(i) - 'A'] = i;
        }
        Arrays.fill(index, s.length());
        for (int i = s.length() - 1; i >= 0; i--) {
            right[i] = index[s.charAt(i) - 'A'];
            index[s.charAt(i) - 'A'] = i;
        }
        var ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += (i - left[i]) * (right[i] - i);
        }
        return ans;
    }
}
