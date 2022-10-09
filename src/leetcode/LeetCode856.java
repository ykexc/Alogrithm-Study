package leetcode;

/**
 * @author 86152
 */
public class LeetCode856 {

    public int scoreOfParentheses(String s) {
        var ans = 0; var count = 0;
        for (var i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            else {
                count--;
                if (s.charAt(i - 1) == '(') {
                    ans += 1 << count;
                }
            }
        }
        return ans;
    }

}
