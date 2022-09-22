package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 86152
 */
public class LeetCode1640 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        var map = new HashMap<Integer, List<Integer>>();
        for (int[] piece : pieces) {
            var list = new ArrayList<Integer>();
            for (int i = 1; i < piece.length; i++) {
                list.add(piece[i]);
            }
            map.put(piece[0], list);
        }
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                for (int j = i + 1, q = 0; j <  i + map.get(arr[i]).size() + 1; j++) {
                    if (arr[j] != map.get(arr[i]).get(q++)) {
                        return false;
                    }
                }
                i += map.get(arr[i]).size();
            } else {
                return false;
            }
        }
        return true;
    }

}
