package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode84 {


    public int largestRectangleArea(int[] heights) {
        var l = new int[heights.length];
        var r = new int[heights.length];
        Arrays.fill(l, -1);
        Arrays.fill(r, heights.length);
        var stack = new ArrayDeque<Integer>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {
                r[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {
                l[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            ans = Math.max(ans, heights[i] * (r[i] - l[i] - 1));
        }
        return ans;
    }


}
