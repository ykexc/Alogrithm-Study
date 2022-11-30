package leetcode;

import java.util.*;

class FreqStack {
    Map<Integer, Integer> vf = new HashMap<>();
    PriorityQueue<int[]> queue;
    int idx = 0;

    public FreqStack() {
        queue = new PriorityQueue<>((a, b) -> a[2] == b[2] ? Integer.compare(b[1], a[1]) : Integer.compare(b[2], a[2]));
    }

    public void push1(int val) {
        vf.put(val, vf.getOrDefault(val, 0) + 1);
        queue.add(new int[]{val, idx++, vf.get(val)});
    }

    public int pop1() {
        int e = Objects.requireNonNull(queue.poll())[0];
        vf.put(e, vf.get(e) - 1);
        return e;
    }

    Map<Integer, List<Integer>> map = new HashMap<>();
    Map<Integer, Integer> cnts = new HashMap<>();
    int max;
    public void push(int val) {
        cnts.put(val, cnts.getOrDefault(val, 0) + 1);
        int c = cnts.get(val);
        List<Integer> list = map.getOrDefault(c, new ArrayList<>());
        list.add(val);
        map.put(c, list);
        max = Math.max(max, c);
    }
    public int pop() {
        List<Integer> list = map.get(max);
        int ans = list.remove(list.size() - 1);
        cnts.put(ans, cnts.get(ans) - 1);
        if (list.size() == 0) max--;
        return ans;
    }
}