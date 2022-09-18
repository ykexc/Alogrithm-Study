package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86152
 */
public class LeetCode2013 {


    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    public void add(int[] point) {
        Map<Integer, Integer> orDefault = map.getOrDefault(point[0], new HashMap<>());
        orDefault.put(point[1], orDefault.getOrDefault(point[1], 0) + 1);
        map.put(point[0], orDefault);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        int ans = 0;
        Map<Integer, Integer> orDefault = map.getOrDefault(x, new HashMap<>());
        for (int yy : orDefault.keySet()) {
            if (yy == y) {
                continue;
            }
            int c1 = orDefault.get(yy);
            int len = y - yy;
            int[] dirs = {x + len, x - len};
            for (int dir : dirs) {
                Map<Integer, Integer> orDefault1 = map.getOrDefault(dir, new HashMap<>());
                int c2 = orDefault1.getOrDefault(y, 0);
                int c3 = orDefault1.getOrDefault(yy, 0);
                ans += c1 * c2 * c3;
            }
        }
        return ans;
    }
}
