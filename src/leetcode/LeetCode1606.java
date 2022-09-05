package leetcode;

import java.util.*;

/**
 * @author 86152
 */
public class LeetCode1606 {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        var max = 0;
        var cnts = new int[100005];
        var free = new TreeSet<Integer>();
        var q = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < k; i++) {
            free.add(i);
        }
        for (int i = 0; i < arrival.length; i++) {
            int start = arrival[i];
            int end = start + load[i];
            while (!q.isEmpty() && q.peek()[1] <= start) {
                free.add(q.poll()[0]);
            }
            Integer s = free.ceiling(i % k);
            if (s == null) {
                s = free.ceiling(0);
            }
            if (s == null) {
                continue;
            }
            free.remove(s);
            q.add(new int[]{s, end});
            cnts[s]++;
            max = Math.max(max, cnts[s]);
        }
        var ans = new ArrayList<Integer>();
        for (int i = 0; i < cnts.length; i++) {
            if (cnts[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}

