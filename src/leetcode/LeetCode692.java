package leetcode;

import java.util.*;


/**
 * @author 86152
 */
public class LeetCode692 {

    public List<String> topKFrequent(String[] words, int k) {
        // 1.初始化 哈希表 key -> 字符串 value -> 出现的次数。
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> {
            if (count.get(a).equals(count.get(b))) {
                return b.compareTo(a);
            } else {
                return count.get(b) - count.get(a);
            }
        });
        queue.addAll(count.keySet());
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(queue.poll());
        }
        return ans;
    }
}
