package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode784 {

    List<String> ans = new ArrayList<>();
    int n;
    public List<String> letterCasePermutation(String s) {
        n = s.length();
        StringBuilder sb = new StringBuilder(s);
        dfs(sb, 0);
        return ans;
    }

    void dfs(StringBuilder sb, int index) {
        if (index < n) {
            ans.add(sb.toString());
        } else return;
        for (int i = index; i < n; i++) {
            if (sb.charAt(i) <= '9') continue;
            sb.setCharAt(i,toUpperOrLower(sb.charAt(i)));
            dfs(sb, i + 1);
            sb.setCharAt(i, toUpperOrLower(sb.charAt(i)));
        }
    }

    public Character toUpperOrLower(Character c) {
        int i;
        if (c >= 'a') {
            i = c - 32;
        } else {
            i = c + 32;
        }
        return (char) i;
    }

}
