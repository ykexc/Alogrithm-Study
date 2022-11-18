package leetcode;

import java.util.Arrays;

/**
 * 数学
 */
public class LeetCode891 {

    private static final int MOD = (int) 1e9 + 7;

    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        var n = nums.length;
        var pow2 = new int[n];
        pow2[0] = 1;
        for (var i = 1; i < n; ++i)
            pow2[i] = pow2[i - 1] * 2 % MOD; // 预处理 2 的幂次
        var ans = 0L;
        for (var i = 0; i < n; ++i)
            ans += (long) (pow2[i] - pow2[n - 1 - i]) * nums[i]; // 在题目的数据范围下，这不会溢出
        return (int) (ans % MOD + MOD) % MOD; // 注意上面有减法，ans 可能为负数
    }
}
