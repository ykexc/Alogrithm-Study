package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class LeetCode1106 {

    public boolean parseBoolExpr(String expression) {

        List<Integer> temp = new ArrayList<>();
        var deque = new ArrayDeque<Character>();
        var deque2 = new ArrayDeque<Character>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ',') continue;
            if (c == '!' || c == '|' || c == '&') {
                deque2.addLast(c);
                continue;
            }
            if (c == ')') {
                while (!deque.isEmpty() && deque.peekLast() != '(') {
                    int i1 = deque.pollLast() == 't' ? 1 : 0;
                    temp.add(i1);
                }
                int calculate = calculate(temp, deque2.pollLast());
                deque.pollLast();
                char cc = calculate == 1 ? 't' : 'f';
                deque.addLast(cc);
                temp.clear();
                continue;
            }
            deque.addLast(c);
        }
        return 't' == deque.peekLast();



    }

    int calculate (List<Integer> list, Character c) {
        if (c == '!') return list.get(0) == 1 ? 0 : 1;
        if (c == '&') {
            int ans = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                ans &= list.get(i);
            }
            return ans;
        }
        int ans = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            ans |= list.get(i);
        }
        return ans;
    }


}
