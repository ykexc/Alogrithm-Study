package leetcode;

/**
 * 差分数组(暂时不会)
 */
public class LeetCode1674 {


    public int minMoves(int[] nums, int limit) {
        int len = nums.length;
        //确定num[i] + nums[len - 1 - i]的范围
        int range = 2 * 100000 + 2;
        //差分数组
        int[] diff = new int[range];

        for (int i = 0; i < len / 2; i++) {
            int lv = nums[i], rv = nums[len - i - 1];
            //假设每一组数都要操作2次
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;
            //和在某个范围之内可以减少操作次数
            diff[1 + Math.min(lv, rv)] -= 1;
            diff[limit + 1 + Math.max(lv, rv)] += 1;

            diff[lv + rv] -= 1;
            diff[lv + rv + 1] += 1;

        }
        int cur = 0, ans = len;
        //得到最少的操作次数
        for (int i = 2; i <= 2 * limit; i++) {
            //System.out.println(diff[i]);
            cur += diff[i];
            ans = Math.min(ans, cur);
        }
        return ans;
    }
}
