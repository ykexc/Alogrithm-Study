package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串匹配
 */
public class LeetCode686 {

    public int repeatedStringMatch(String a, String b) {
        if (a.contains(b)) return 1;
        int n = a.length(), m = b.length();
        if (!check(a, b)) return -1;
        int i = m / n + 3;
        String repeat = a.repeat(i);
        if (!repeat.contains(b)) return -1;
        for (int j = i - 1; j >= 0; j--) {
            if (!a.repeat(j).contains(b)) {
                return j + 1;
            }
        }
        return -1;
    }

    boolean check(String a, String b) {
        Set<Character> set = new HashSet<>();
        for (char c : a.toCharArray()) set.add(c);
        for (char c : b.toCharArray()) if (!set.contains(c)) return false;
        return true;
    }
}
