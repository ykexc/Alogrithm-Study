package leetcode;

import java.util.*;

public class LeetCode815 {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < routes.length; i++) {
            for (int x : routes[i]) {
                map.computeIfAbsent(x, key -> new ArrayList<>()).add(i);
                if (x == source) {
                    set.add(i);
                    queue.addLast(new int[]{i, 1});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.pollFirst();
            int st = poll[0], v = poll[1];
            for (int s : routes[st]) {
                if (s == target) {
                    return v;
                } else {
                    List<Integer> lis = map.get(s);
                    if (lis != null) {
                        for (int a : lis) {
                            if (set.contains(a)) continue;
                            set.add(a);
                            queue.addLast(new int[]{a, v + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }

}
