package leetcode;

/**
 * 找规律，构造
 */
public class LeetCode481 {

    public int magicalString(int n) {
        var s = new StringBuilder("122");
        var sx = "1";
        var ans = 0;
        for (var i = 2; i < n; i++) {
            if (s.charAt(i) == '1') {
                s.append(sx.repeat(1));
            } else s.append(sx.repeat(2));
            sx = "1".equals(sx) ? "2" : "1";
        }
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == '1') ans++;
        return ans;
    }

}
