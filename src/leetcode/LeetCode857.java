package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * @author 86152
 */
public class LeetCode857 {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double ans = -1;
        int total = 0;
        Integer[] integers = IntStream.range(0, quality.length).boxed().toArray(Integer[]::new);
        Arrays.sort(integers, (a, b) -> wage[a] * quality[b] - wage[b] * quality[a]);
        var heap = new PriorityQueue<Integer>((a, b) -> b - a);
        for (Integer integer : integers) {
            total += quality[integer];
            heap.add(quality[integer]);
            if (heap.size() > k) {
                total -= heap.poll();
            }
            if (heap.size() == k) {
                double cur = (double) total * wage[integer] / quality[integer];
                ans = ans < 0 ? cur : Math.min(ans, cur);
            }
        }
        return ans;
    }
}
