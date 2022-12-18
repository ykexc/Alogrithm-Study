package leetcode;

import java.util.*;

/**
 * 字符串匹配
 */
public class LeetCode187 {

    public List<String> findRepeatedDnaSequences(String s) {
        var n = s.length();
        if (n <= 10) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        Set<String> ret = new HashSet<>();
        for (int i = 10; i <= n; i++) {
            String ss = s.substring(i - 10, i);
            if (set.contains(ss)) {
                ret.add(ss);
            } else {
                set.add(ss);
            }
        }
        return new ArrayList<>(ret);
    }

    /**
     * 字符串哈希
     */
    int N = (int) 1e5 + 10, P = 131313;
    int[] h = new int[N], p = new int[N];

    public List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        char[] array = s.toCharArray();
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + array[i - 1];
            p[i] = p[i - 1] * P;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i + 10 - 1 <= n; i++) {
            int j = i + 10 - 1;
            int hash = h[j] - h[i - 1] * p[j - i + 1];
            int cnt = map.getOrDefault(hash, 0);
            if (cnt == 1) ans.add(s.substring(i - 1, i + 10 - 1));
            map.put(hash, cnt + 1);
        }
        return ans;
    }

}
