package leetcode;

import java.util.Arrays;

public class LeetCode1235 {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        var n = startTime.length;
        var task = new int[n][3];
        for (var i = 0; i < n; i++) {
            task[i][0] = startTime[i];
            task[i][1] = endTime[i];
            task[i][2] = profit[i];
        }
        Arrays.sort(task, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        var dp = new int[n + 1];
        dp[0] = 0;
        for (var i = 1; i <= n; i++) {
            int k = binarySearch(task, i - 1);
            dp[i] = Math.max(dp[i - 1], dp[k] + task[i - 1][2]);
        }
        return dp[n];
    }

    int binarySearch(int[][] task, int right) {
        var target = task[right][0];
        var left = 0;
        while (left < right) {
           var mid = left + (right - left) / 2;
           if (task[mid][1] <= target) {
               left = mid + 1;
           } else right = mid;
        }
        return left;
    }

}
