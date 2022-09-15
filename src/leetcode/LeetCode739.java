package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode739 {

    public int[] dailyTemperatures(int[] temperatures) {
        var ans = new int[temperatures.length];
        Arrays.fill(ans, 0);
        var stack = new ArrayDeque<Integer>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peekLast()] < temperatures[i]) {
                int n = stack.pollLast();
                ans[n] = i - n;
            }
            stack.addLast(i);
        }
        return ans;
    }


}
