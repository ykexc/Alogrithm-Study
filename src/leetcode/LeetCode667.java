package leetcode;

/**
 * @author 86152
 */
public class LeetCode667 {

    public int[] constructArray(int n, int k) {
        var ans = new int[n];
        int m = n - (k + 1);
        for (int i = 0; i < m; i++) {
            ans[i] = i + 1;
        }
        for (int i = m , a = n, b = m + 1; i < n; ) {
            ans[i++] = b++;
            if (i < n) {
                ans[i++] = a--;
            }
        }
        return ans;
    }
}
