package leetcode;

public class LeetCode738 {

    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        var chars = s.toCharArray();
        for (var i = chars.length - 2; i >= 0; i--) {
            if (chars[i] > chars[i + 1]) {
                chars[i] = (char) (chars[i] - 1);
                for (int j = i + 1; j < chars.length; j++) chars[j] = '9';
            }
        }
        return Integer.parseInt(new String(chars));
    }

}
