package leetcode;

import java.util.Arrays;

public class LeetCode1799 {


    int[] nums;
    int n;
    int[][] gcd_value;
    int[] state;

    public int maxScore(int[] nums) {
        this.nums = nums;
        n = nums.length;
        gcd_value = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd_value[i][j] = gcd(nums[i], nums[j]);
            }
        }

        state = new int[1 << n];
        Arrays.fill(state, -1);
        dfs((1 << n) - 1, n / 2);

        return state[(1 << n) - 1];
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    public int dfs(int current_state, int id) {
        if (current_state == 0) {
            return 0;
        }

        if (state[current_state] != -1) {
            return state[current_state];
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (((current_state >> i) & 1) != 1) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (((current_state >> j) & 1) != 1) {
                    continue;
                }
                sum = Math.max(sum, id * gcd_value[i][j] + dfs(current_state ^ (1 << i) ^ (1 << j), id - 1));
            }
        }
        state[current_state] = sum;
        return sum;
    }

}
