package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * bfs
 */
public class LeetCode2059 {
    public int minimumOperations(int[] nums, int start, int goal) {

        Deque<int[]> deque = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        set.add(start);
        deque.add(new int[]{start, 0});
        while (!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            if (temp[0] == goal) {
                return temp[1];
            }
            if (temp[0] > 1000 || temp[0] < 0) continue;
            for (var num : nums) {
                int x = num + temp[0];
                int y = temp[0] - num;
                int z = num ^ temp[0];
                int s = temp[1] + 1;
                if (!set.contains(x)) {
                    set.add(x);
                    deque.addLast(new int[]{x, s});
                }
                if (!set.contains(y)) {
                    set.add(y);
                    deque.addLast(new int[]{y, s});
                }
                if (!set.contains(z)) {
                    set.add(z);
                    deque.addLast(new int[]{z, s});
                }
            }
        }
        return -1;
    }

}
