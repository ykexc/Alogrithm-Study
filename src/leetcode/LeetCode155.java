package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86152
 */
public class LeetCode155 {

    Deque<int[]> stack = new ArrayDeque<>();

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.addLast(new int[]{val, val});
        }
        stack.addLast(new int[]{val, Math.min(val, stack.peekLast()[1])});
    }

    public void pop() {
        stack.pollLast();
    }

    public int top() {
        return stack.peekLast()[0];
    }

    public int getMin() {
        return stack.peekLast()[1];
    }
}
