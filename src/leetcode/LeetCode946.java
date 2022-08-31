package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86152
 */
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Deque<Integer> deque = new ArrayDeque<>();
        int x = 0;
        for (int i = 0; i < n; i++) {
            deque.addLast(pushed[i]);
            while (!deque.isEmpty() && x < popped.length && deque.peekLast() == popped[x]) {
                deque.pollLast();
                x++;
            }
        }
        return deque.isEmpty();
    }
}