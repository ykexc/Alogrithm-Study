package leetcode;

public class LeetCode754 {

    public int reachNumber(int target) {
        target = Math.abs(target);
        int sum = 0;
        int ans = 0;
        for (int i = 1; i <= target; i++) {
            if (sum == target) return ans;
            else if (sum > target) break;
            sum += i;
            ans++;
        }
        int k = sum - target;
        if (k % 2 == 0) return ans;
        return ans + 2;
    }

}
