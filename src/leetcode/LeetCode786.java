package leetcode;

import java.util.PriorityQueue;

/**
 * @author 86152
 * 多路归并
 */
public class LeetCode786 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        var heap = new PriorityQueue<int[]>((a, b) -> arr[a[0]] * arr[b[1]] - arr[a[1]] * arr[b[0]]);
        for (int i = 1; i < arr.length; i++) {
            heap.add(new int[]{0, i});
        }
        for (int i = 1; i < k; i++) {
            var poll = heap.poll();
            if (poll[0] + 1 < poll[1]) {
                heap.add(new int[]{poll[0] + 1, poll[1]});
            }
        }
        return new int[]{arr[heap.peek()[0]], arr[heap.peek()[1]]};
    }
}
