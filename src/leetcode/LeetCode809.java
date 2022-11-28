package leetcode;

/**
 * two pointers
 */
public class LeetCode809 {


    String _s;

    public int expressiveWords(String s, String[] words) {
        var ans = 0;
        _s = s;
        for (var word : words) if (check(word)) ans++;
        return ans;
    }

    boolean check(String s) {
        var i = 0;
        var j = 0;
        while (i < _s.length() && j < s.length()) {
            if (_s.charAt(i) != s.charAt(j)) return false;
            char c = s.charAt(j);
            var t = 0;
            while (i < _s.length() && _s.charAt(i) == c) {
                i++;
                t++;
            }
            int t2 = 0;
            while (j < s.length() && s.charAt(j) == c) {
                j++;
                t2++;
            }
            if (t < t2) return false;
            if (t > t2 && t < 3) return false;
        }
        return i == _s.length() && j == s.length();
    }

}
