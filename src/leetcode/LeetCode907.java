package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode907 {
    int mod = (int) 1e9 + 7;

    public int sumSubarrayMins(int[] arr) {
        var r = new int[arr.length];
        var l = new int[arr.length];
        Arrays.fill(l, -1);
        Arrays.fill(r, arr.length);
        var stack = new ArrayDeque<Integer>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peekLast()] >= arr[i]) {
                r[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        stack.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peekLast()] > arr[i]) {
                l[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += (long) (i - l[i]) * (r[i] - i) * arr[i] % mod;
            ans %= mod;
        }
        return (int) ans;
    }
}
