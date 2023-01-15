package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode318 {

    Map<String, Integer> map = new HashMap<>();

    public int maxProduct(String[] words) {
        for (var word : words) {
            int x = 0;
            for (char c : word.toCharArray()) {
                x |= ((c - 'a') << 1);
            }
            map.put(word, x);
        }
        int ans = 0, n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int a = words[i].length(), b = words[j].length();
                if (a * b <= ans || (map.get(words[i]) & map.get(words[j])) != 0) continue;
                ans = Math.max(a * b, ans);
            }
        }
        return ans;
    }
}
