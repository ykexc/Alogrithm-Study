package leetcode;

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
        int index = -1;
        var stack1 = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            while (index != -1 && heights[stack1[index]] > heights[i]) {
                r[stack1[index--]] = i;
            }
            stack1[++index] = i;
        }
        stack1 = new int[heights.length];
        index = -1;
        for (int i = heights.length - 1; i >= 0; i--) {
            while (index != -1 && heights[stack1[index]] > heights[i]) {
                l[stack1[index--]] = i;
            }
            stack1[++index] = i;
        }
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            ans = Math.max(ans, heights[i] * (r[i] - l[i] - 1));
        }
        return ans;
    }


}
