package leetcode;

import java.util.*;

public class LeetCode30 {

    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length, w = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);
        List<Integer> ans = new ArrayList<>();
        out:
        for (int i = 0; i + m * w <= n; i++) {
            Map<String, Integer> cur = new HashMap<>();
            String sub = s.substring(i, i + m * w);
            for (int j = 0; j < sub.length(); j += w) {
                String item = sub.substring(j, j + w);
                if (!map.containsKey(item)) continue out;
                cur.put(item, cur.getOrDefault(item, 0) + 1);
            }
            if (cur.equals(map)) ans.add(i);
        }
        return ans;
    }


    public List<Integer> findSubstring2(String s, String[] words) {
        int n = s.length(), m = words.length, w = words[0].length();
        // 统计 words 中「每个目标单词」的出现次数
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) map.put(str, map.getOrDefault(str, 0) + 1);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            // 构建一个当前子串对应的哈希表，统计当前子串中「每个目标单词」的出现次数
            Map<String, Integer> temp = new HashMap<>();
            // 滑动窗口的大小固定是 m * w，每次将下一个单词添加进 temp，上一个单词移出 temp
            for (int j = i; j + w <= n; j += w) {
                String cur = s.substring(j, j + w);
                temp.put(cur, temp.getOrDefault(cur, 0) + 1);
                if (j >= i + (m * w)) {
                    int idx = j - m * w;
                    String prev = s.substring(idx, idx + w);
                    if (temp.get(prev) == 1) temp.remove(prev);
                    else temp.put(prev, temp.get(prev) - 1);
                    if (!temp.getOrDefault(prev, 0).equals(map.getOrDefault(prev, 0))) continue;
                }
                if (!temp.getOrDefault(cur, 0).equals(map.getOrDefault(cur, 0))) continue;
                // 上面两个 continue 可以减少 map 之间的 equals 操作
                if (temp.equals(map)) ans.add(j - (m - 1) * w);
            }
        }
        return ans;
    }

    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (words[0].length() > s.length()) {
            return list;
        }
        HashMap<String, Integer> mapp = new HashMap<>();
        for (var word : words) mapp.merge(word, 1, Integer::sum);
        int n = words[0].length();
        int c = 0;
        while (c + words[0].length() * words.length <= s.length()) {
            HashMap<String, Integer> temp = new HashMap<>();
            int m = c;
            while (m + n <= s.length()) {
                String substring = s.substring(m, m + n);
                if (mapp.containsKey(substring)) {
                    temp.merge(substring, 1, Integer::sum);
                    m += n;
                } else {
                    break;
                }
                if (temp.equals(mapp)) {
                    list.add(c);
                    break;
                }
            }
            c++;
        }
        return list;
    }
}