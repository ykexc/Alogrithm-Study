package leetcode;

import java.util.Arrays;

/**
 * 贪心,序列DP,LIS
 */
public class LeetCode1691 {

    public int maxHeight(int[][] cuboids) {
        for (int[] c : cuboids)
            Arrays.sort(c);
        Arrays.sort(cuboids, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        int ans = 0, n = cuboids.length;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) f[i] = cuboids[i][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j)
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2])
                    f[i] = Math.max(f[i], f[j] + cuboids[i][2]); // cuboids[j] 可以堆在 cuboids[i] 上
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
