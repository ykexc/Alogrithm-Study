package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode140 {

    List<String> ans;
    Set<String> set;
    String _s;
    public List<String> wordBreak(String s, List<String> wordDict) {
        ans = new ArrayList<>();
        set = new HashSet<>(wordDict);
        _s = s;
        dfs("", 0);
        return ans;
    }
    void dfs(String s, int start) {
        if (start == _s.length()) {
            ans.add(s.substring(0,s.length() - 1));
        }
        for (int i = start + 1; i <= _s.length(); i++) {
            if (set.contains(_s.substring(start, i))) {
                dfs(s + _s.substring(start, i) + " ", i);
            }
        }
    }
}
