package leetcode;

import java.util.HashSet;

/**
 * @author 86152
 */
public class LeetCode6177 {

    public int partitionString(String s) {
        var set = new HashSet<Character>();
        int ans = 1;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                ans++;
                set.clear();
            }
            set.add(c);
        }
        return ans;
    }
}
