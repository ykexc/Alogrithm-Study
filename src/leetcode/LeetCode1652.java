package leetcode;

/**
 * @author 86152
 */
public class LeetCode1652 {

    public int[] decrypt(int[] code, int k) {
        var ans = new int[code.length];
        if (k == 0) return ans;
        var sum = new int[code.length * 2 + 1];
        for (int i = 0; i < code.length * 2; i++) {
            sum[i + 1] = code[i % code.length] + sum[i];
        }
        for (int i = 0; i < code.length; i++) {
            if (k > 0) {
                ans[i] = sum[i + k + 1] - sum[i + 1];
            } else {
                ans[i] = sum[i + code.length] - sum[code.length + i + k];
            }
        }
        return ans;
    }

}
