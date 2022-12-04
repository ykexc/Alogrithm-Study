package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数学,滑动窗口
 */
public class LeetCode1610 {

    static double eps = 1e-9;
    static double PI = Math.PI;

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        var x = location.get(0);
        var y = location.get(1);
        var list = new ArrayList<Double>();
        var ans = 0;
        for (var point : points) {
            var a = point.get(0);
            var b = point.get(1);
            if (a.equals(x) && b.equals(y)) {
                ans++;
                continue;
            }
            list.add(Math.atan2(b - y, a - x) + PI);
        }
        Collections.sort(list);
        var n = list.size();
        for (int i = 0; i < n; i++) {
            list.add(list.get(i) + 2 * PI);
        }
        var max = 0;
        double angelTr = PI * angle / 180;
        for (int r = 0, l = 0; r < 2 * n; r++) {
            while (l < r && list.get(r) - list.get(l) >= angelTr + eps) l++;//多做，多总结
            max = Math.max(r - l + 1, max);
        }
        return ans + max;
    }

}
