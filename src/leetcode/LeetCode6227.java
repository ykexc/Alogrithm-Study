package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 单调栈/堆
 */
public class LeetCode6227 {
    /**
     * 三栈解法
     *
     * @param nums
     * @return
     */
    public int[] secondGreaterElement(int[] nums) {
        var n = nums.length;
        var res = new int[n];
        Arrays.fill(res, -1);
        var s1 = new ArrayDeque<Integer>();
        var s2 = new ArrayDeque<Integer>();
        var tem = new ArrayDeque<Integer>();
        for (var i = 0; i < n; i++) {//利用迭代性质巧妙解法
            while (!s2.isEmpty() && nums[s2.peekLast()] < nums[i])
                res[s2.pollLast()] = nums[i];
            while (!s1.isEmpty() && nums[s1.peekLast()] < nums[i])
                tem.addLast(s1.pollLast());
            while (!tem.isEmpty()) s2.addLast(tem.pollLast());
            s1.addLast(i);
        }
        return res;
    }

    /**
     * 单调栈和堆
     *
     * @param nums
     * @return
     */
    public int[] secondGreaterElement2(int[] nums) {
        var n = nums.length;
        var res = new int[n];
        Arrays.fill(res, -1);
        var s1 = new ArrayDeque<Integer>();
        var q = new PriorityQueue<Integer>((a, b) -> nums[a] - nums[b]);
        for (int i = 0; i < n; i ++ ) {
            while (!q.isEmpty() && nums[q.peek()] < nums[i])
                res[q.poll()] = nums[i];
            while (!s1.isEmpty() && nums[s1.peekLast()] < nums[i])
                q.add(s1.pollLast());
            s1.addLast(i);
        }
        return res;
    }
}
