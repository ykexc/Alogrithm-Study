package leetcode;

public class LeetCode666 {

    Integer[] map = new Integer[100];
    int ans = 0;
    public int pathSum(int[] nums) {
        for (int num : nums) map[num / 10] = num % 10;
        dfs(11, 0);
        return ans;
    }

    void dfs(int key, int sum) {
        sum += map[key];
        int left = (key / 10 + 1) * 10 + (key % 10) * 2 - 1;
        int right = left + 1;
        if (map[left] == null && map[right] == null) {
            ans += sum;
        } else {
            if (map[left] != null) dfs(left, sum);
            if (map[right] != null) dfs(right, sum);
        }
    }


}
