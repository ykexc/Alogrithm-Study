package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author 86152
 */
public class LeetCode1190 {

    public String reverseParentheses(String s) {
        var stack = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ')') {
                stack.addLast(s.charAt(i));
            } else {
                var temp = new ArrayList<Character>();
                while (!stack.isEmpty()) {
                    var c = stack.peekLast();
                    if (c == '(') {
                        stack.pollLast();
                        break;
                    } else {
                        temp.add(stack.pollLast());
                    }
                }
                for (Character character : temp) {
                    stack.addLast(character);
                }
            }
        }
        var ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.pollFirst());
        }
        return ans.toString();
    }
}
