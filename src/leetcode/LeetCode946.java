package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86152
 */
class Solution {
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
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

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int[] stack = new int[pushed.length];
        int index = -1;
        int k = 0;
        for(int i = 0; i < n; i ++) {
            stack[++index] = pushed[i];
            while(index != -1 && k < n && popped[k] == stack[index]) {
                index --;
                k ++;
            }
        }
        return index == -1;
    }
}