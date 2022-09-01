package leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author 86152
 */
public class LeetCode451 {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new TreeMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for(Map.Entry<Character, Integer> m : map.entrySet()) {
            priorityQueue.add(new int[] {m.getKey(), m.getValue()});
        }
        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            int[] temp = priorityQueue.poll();
            for (int i = 0; i < temp[1]; i++) {
                sb.append((char) temp[0]);
            }
        }
        return sb.toString();
    }


    public String frequencySort2(String s) {
        int[][] arr = new int[128][2];
        for (int i = 0; i < 128; i++) {
            arr[i][0] = i;
        }
        for(char c : s.toCharArray()) {
            arr[c][1] ++;
        }
        Arrays.sort(arr, (a, b) -> {
            return a[1] != b[1] ? b[1] - a[1] : a[0] - b[0];
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 123; i++) {
            var k = arr[i][1];
            while (k != 0) {
                sb.append((char) arr[i][0]);
                k --;
            }
        }
        return sb.toString();
    }
}
