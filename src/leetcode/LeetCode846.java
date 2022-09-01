package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author 86152
 */
public class LeetCode846 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
            priorityQueue.add(h);
        }
        while (!priorityQueue.isEmpty()) {
            int poll = priorityQueue.poll();
            if (map.get(poll) == 0) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int temp = map.getOrDefault(poll + i, 0);
                if (temp == 0) {
                    return false;
                }
                map.put(poll + i, map.get(poll + i) - 1);
            }
        }
        return true;
    }
}
