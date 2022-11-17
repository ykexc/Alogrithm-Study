package leetcode;


import java.util.*;

/**
 * 二分,哈希表
 */
public class LeetCode792 {

    public int numMatchingSubseq(String s, String[] words) {
        var map = new HashMap<Character, ArrayList<Integer>>();
        for (int i = 0; i < s.length(); i++) map.computeIfAbsent(s.charAt(i), key -> new ArrayList<>()).add(i);
        var ans = 0;
        for (String word : words) {
            var flag = true;
            var index = -1;
            var m = word.length();
            for (int i = 0; i < m && flag; i++) {
                if (!map.containsKey(word.charAt(i))) {
                    flag = false;
                    break;
                }
                ArrayList<Integer> list = map.get(word.charAt(i));
                var l = 0;
                var r = list.size() - 1;
                while (l < r) {
                    var mid = l + r >> 1;
                    if (list.get(mid) <= index) {
                        l = mid + 1;
                    } else r = mid;
                }
                if (list.get(l) <= index) {
                    flag = false;
                }
                index = list.get(l);
            }
            if (flag) ans++;
        }
        return ans;
    }


}
