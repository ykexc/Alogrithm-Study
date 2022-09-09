package leetcode;

/**
 * @author 86152
 */
public class LeetCode1598 {

    public int minOperations(String[] logs) {
        int ans = 0;
        for (String s : logs) {
            if (s.startsWith("../")) {
                ans--;
                ans = Math.max(ans, 0);
            } else if (s.startsWith("./")) {
                continue;
            } else {
                ans++;
            }
        }
        return ans;
    }
}
