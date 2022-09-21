package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        boolean[] used = new boolean[len];
        Arrays.sort(nums);

        // 1、排除
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }

        // 2、
        int target = sum / k;
        if (nums[len - 1] > target) {
            return false;
        }
        return dfs(nums, 0, target, 0, k, used);
    }

    public static boolean dfs(int[] nums, int index, int target, int curSum, int k, boolean[] used) {
        if (k == 1) {
            return true;
        }
        // 只有满足每一段都相等，再继续进行dfs构建：这里curSum重新以0进入下一层
        if (curSum == target) { // 这里的dfs是确认当前段满足target，进入下一段
            return dfs(nums, 0, target, 0, k - 1, used);
        }

        // 当前段的组合：在当前段index只会一直减小（只有进入下一段index才会重新初始化）
        for (int i = index; i < nums.length; i++) {
            if (used[i]) { // 每个元素只能使用一次
                continue;
            }
            if (curSum + nums[i] > target) { // 当前凑得的和大了，需要加的数应该小一点
                continue;
            }
            used[i] = true; // 标记访问过
            // 这里的dfs是为了搜寻当前段符合条件的可能：
            if (dfs(nums, i + 1, target, curSum + nums[i], k, used)) {
                return true;
            }
            used[i] = false;
            // 连续添加两个相同的数都无法满足条件，就没必要继续添加了
//            while (i > 0 && nums[i - 1] == nums[i]) {
//                i--;
//            }
        }
        return false;
    }

}
