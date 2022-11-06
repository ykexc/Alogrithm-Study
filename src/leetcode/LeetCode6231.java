package leetcode;

import java.util.PriorityQueue;

/**
 * 堆模拟
 */
public class LeetCode6231 {

    public long totalCost(int[] costs, int k, int candidates) {
        long sum = 0, i = 0, j = costs.length - 1;
        PriorityQueue<Integer> left = new PriorityQueue<>(), right = new PriorityQueue<>();
        while (left.size() < candidates) {
            left.offer(costs[(int) i++]);
        }
        while (right.size() < candidates) {
            right.offer(j < i ? Integer.MAX_VALUE : costs[(int) j--]);
        }
        for (; k > 0; k--) {
            if (right.peek() < left.peek()) {
                sum += right.poll();
                right.offer(j < i ? Integer.MAX_VALUE : costs[(int) j--]);
            } else {
                sum += left.poll();
                left.offer(i > j ? Integer.MAX_VALUE : costs[(int) i++]);
            }
        }
        return sum;
    }

}
