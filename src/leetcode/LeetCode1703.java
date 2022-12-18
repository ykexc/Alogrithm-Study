package leetcode;

/**
 * 贪心，数学，条件转换
 */
public class LeetCode1703 {

    public int minMoves(int[] nums, int k) {
        var n = nums.length;
        var arr = new int[n];
        var index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) arr[index++] = i;
        }
        int ans, c1 = 0, mid = k / 2;
        for (int i = 1; i < k; i++) c1 += (arr[i] - arr[i - 1] - 1) * Math.min(i, k - i);
        ans = c1;
        for (int i = k; i < index; i++) {
            c1 -= arr[i - k + mid] - arr[i - k];
            c1 += arr[i] - arr[i - mid];
            ans = Math.min(ans, c1);
        }
        return ans;
    }

}
