package leetcode;


import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 单调栈， 区间DP，枚举
 */

public class LeetCode2104 {


    //单调栈解法
    public long subArrayRanges(int[] nums) {
        var n = nums.length;
        var lmax = new int[n];
        var lmin = new int[n];
        var rmax = new int[n];
        var rmin = new int[n];
        Arrays.fill(lmax, -1);
        Arrays.fill(lmin, -1);
        Arrays.fill(rmax, n);
        Arrays.fill(rmin, n);
        var deque = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                rmax[deque.pollLast()] = i;
            }
            deque.addLast(i);
        }
        deque.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                lmax[deque.pollLast()] = i;
            }
            deque.addLast(i);
        }
        deque.clear();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] >= nums[i]) {
                rmin[deque.pollLast()] = i;
            }
            deque.addLast(i);
        }
        deque.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                lmin[deque.pollLast()] = i;
            }
            deque.addLast(i);
        }
        var ans = 0L;
        for (int i = 0; i < n; i++) {
            ans += nums[i] * ((long) (rmax[i] - i) * (i - lmax[i]) - (long) (rmin[i] - i) * (i - lmin[i]));
        }
        return ans;
    }

    //区间DP
    public long subArrayRanges1(int[] nums) {
        var n = nums.length;
        var f = new int[n + 5][n + 5][2];
        for (int i = 0; i < n; i++) f[i][i][0] = f[i][i][1] = nums[i];
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                var r = l + len - 1;
                f[l][r][0] = Math.min(f[l][r - 1][0], nums[r]);
                f[l][r][1] = Math.max(f[l][r - 1][1], nums[r]);
            }
        }
        var ans = 0L;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += f[i][j][1] - f[i][j][0];
            }
        }
        return ans;
    }

    //枚举
    public long subArrayRanges3(int[] arr) {
        int n = arr.length;
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int min = arr[i], max = arr[i];
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                ans += max - min;
            }
        }
        return ans;
    }

}
