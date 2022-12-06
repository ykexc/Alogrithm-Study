package leetcode;

/**
 * 动态规划，
 */
public class LeetCode1678 {

    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length, left = 0;
        int count = 2, w = boxes[0][1];
        int[] dp = new int[n + 1];
        dp[1] = 2;
        for (int i = 1; i < n; i++) {
            if (boxes[i - 1][0] != boxes[i][0]) count++;
            w += boxes[i][1];
            while (i - left + 1 > maxBoxes || w > maxWeight || dp[left + 1] == dp[left]) {
                w -= boxes[left++][1];
                if (boxes[left - 1][0] != boxes[left][0]) count--;
            }
            dp[i + 1] = dp[left] + count;
        }
        return dp[n];
    }

}
