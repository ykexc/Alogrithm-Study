package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode93 {
    List<String> ans;

    String _s;
    public List<String> restoreIpAddresses(String s) {
        _s = s;
        var n = s.length();
        if (n > 12 || n < 4) return Collections.emptyList();
        ans = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            for (int j = i + 1; j <= i + 3 && j < n - 1; j++) {
                for (int k = j + 1; k <= j + 3 && k < n; k++) {
                    if (j - i > 3 || k - j > 3 || n - k > 3) continue;
                    check(i, j, k);
                }
            }
        }
        return ans;
    }
    void check(int i, int j, int k) {
        String s1 = _s.substring(0, i);
        String s2 = _s.substring(i, j);
        String s3 = _s.substring(j, k);
        String s4 = _s.substring(k);
        if (check(s1) && check(s2) && check(s3) && check(s4)) {
            ans.add(s1 + "." + s2 + "." + s3 + "." + s4);
        }
    }

    boolean check(String s) {
        if (s.length() == 1) return true;
        if (s.charAt(0) == '0') return false;
        if (s.length() > 3) return false;
        return Integer.parseInt(s) <= 255;
    }
}
