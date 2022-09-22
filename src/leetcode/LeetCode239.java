package leetcode;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * @author 86152
 */
public class LeetCode239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        var priorityQueue = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
        var ans = new int[nums.length - k + 1];
        var index = 0;
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.add(new int[]{i, nums[i]});
            if (i >= k - 1) {
                while (priorityQueue.peek()[0] <= i - k) {
                    priorityQueue.poll();
                }
                ans[index++] = priorityQueue.peek()[1];
            }
        }
        return ans;
    }

    /**
     * 单调队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        var deque = new ArrayDeque<Integer>();
        var ans = new int[nums.length - k + 1];
        for (var i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (i >= k - 1) {
                while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }

}
