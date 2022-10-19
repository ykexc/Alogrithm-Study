package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86152
 */
public class LeetCode22 {

    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return ans;
    }

    private void dfs(int n, int n1, String s) {
        if (n == 0 && n1 == 0) {
            ans.add(s);
            return;
        }
        if (n1 < n) return;

        if (n > 0) {
            dfs(n - 1, n1, s + "(");
        }
        if (n1 > 0) {
            dfs(n,n1 - 1, s + ")");
        }
    }


}
