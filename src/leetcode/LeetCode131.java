package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode131 {

    List<List<String>> ans = new ArrayList<>();
    int n;
    String _s;


    public List<List<String>> partition(String s) {
        n = s.length();
        _s = s;
        dfs(0, new ArrayList<>());
        return ans;
    }

    void dfs(int index, List<String> lis) {
        if (index == n) {
            ans.add(new ArrayList<>(lis));
            return;
        }
        for (int i = index + 1; i <= n; i++) {
            String substring = _s.substring(index, i);
            if (check(substring)) {
                lis.add(substring);
                dfs(i, lis);
                lis.remove(lis.size() - 1);
            }
        }
    }

    boolean check(String s) {
        char[] chars = s.toCharArray();
        var l = 0;
        var r = chars.length - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else return false;
        }
        return true;
    }
}
