package leetcode;

import java.util.*;

/**
 * @author 86152
 */
public class LeetCode380 {
    int[] length = new int[200010];
    int ix = -1;
    Map<Integer, Integer> map = new HashMap<>();
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, ++ix);
        length[ix] = val;
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int newVal = length[ix];
        map.put(newVal, index);
        length[index] = newVal;
        map.remove(val);
        ix --;
        return true;
    }

    public int getRandom() {
        return length[new Random().nextInt(ix + 1)];
    }
}
