package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode85 {

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        var sum = new int[n + 1][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j] = matrix[i][j] == '1' ? sum[i][j] + 1 : 0;
            }
        }
        int ans = 0; int index = -1;
        var stack = new int[250];
        for (int i = 1; i <= n; i++) {
            int[] temp = sum[i];
            int[] l = new int[m], r = new int[m];
            Arrays.fill(l, -1); Arrays.fill(r, m);
            for (int j = 0; j < m; j++) {
                while (index != -1 && temp[stack[index]] > temp[j]) {
                    r[stack[index--]] = j;
                }
                stack[++index] = j;
            }
            index = -1;
            for (int j = m - 1; j >= 0; j--) {
                while (index != -1 && temp[stack[index]] > temp[j]) {
                    l[stack[index--]] = j;
                }
                stack[++index] = j;
            }
            index = -1;
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, (r[j] - l[j] - 1) * temp[j]);
            }
        }
        return ans;
    }


}
