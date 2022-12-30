package leetcode;

public class LeetCode1759 {

    static int[] f = new int[100005];

    static {
        f[1] = 1;
        for (int i = 2; i <= 100000; i++) {
            f[i] = 2 * f[i - 1] + 1;
        }
    }


    public int countHomogenous(String s) {
        var ans = 0;
        char[] chars = s.toCharArray();
        int i = 0, j = 0;
        for (; i < s.length(); i++) {
            if (chars[j] != chars[i]) {
                ans += f[i - j];
                j = i;
            }
        }
        ans += f[i - j];
        return ans;
    }

}
