package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86152
 */
public class LeetCode89 {

    public List<Integer> grayCode(int n) {
        var ans = new ArrayList<Integer>();
        for (int i = 0; i < 1 << n; i++) {
            ans.add(i ^ i >> 1);
        }
        return ans;
    }
}
