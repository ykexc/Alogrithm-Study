package leetcode;

import java.util.*;

/**
 * @author 86152
 */
public class LeetCode17 {

    String[] numbers = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> ans = new ArrayList<>();
    String digits;

    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) return ans;
        this.digits = digits;
        dfs("", 0);
        return ans;
    }

    private void dfs(String s, int index) {
        if (index == digits.length()) {
            ans.add(s);
            return;
        }
        int in = digits.charAt(index) - '0' - 2;
        for (char c : numbers[in].toCharArray()) {
            dfs(s + String.valueOf(c), index + 1);
        }
    }

    public List<String> letterCombinations2(String digits) {
        var ans = new ArrayList<String>();
        if ("".equals(digits)) return ans;
        var map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        StringBuilder stringBuilder = new StringBuilder();
        dfs2(digits, stringBuilder, ans, 0, map);
        return ans;
    }

    void dfs2(String digits, StringBuilder sb, List<String> ans, int index, Map<Character, String> map) {
        if (sb.length() == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        char c = digits.charAt(index);
        String s = map.get(c);
        for (char cc : s.toCharArray()) {
            sb.append(cc);
            dfs2(digits, sb, ans, index + 1, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
