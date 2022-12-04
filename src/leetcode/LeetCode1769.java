package leetcode;

/**
 * 前缀和
 */
public class LeetCode1769 {

    public int[] minOperations(String boxes) {
        var n = boxes.length();
        var ans = new int[n];
        var l = new int[n];
        var r = new int[n];
        var t = 0;
        var x = 0;
        for (int i = 1; i < n; i++) {
            x += boxes.charAt(i - 1) == '1' ? 1 : 0;
            t = t + x;
            l[i] = t;
        }
        t = 0;
        x = 0;
        for (int i = n - 2; i >= 0; i--) {
            x += boxes.charAt(i + 1) == '1' ? 1 : 0;
            t = t + x;
            r[i] = t;
        }
        for (int i = 0; i < n; i++) {
            ans[i] = l[i] + r[i];
        }
        return ans;
    }

}
