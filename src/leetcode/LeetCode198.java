package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode198 {

    public int rob(int[] nums) {
        return robHelper(nums, nums.length - 1, new HashMap<>());
    }


    private int robHelper(int[] nums, int i, Map<Integer, Integer> map) {
        //终止条件
        if (i < 0)
            return 0;

        int lastLast;
        int last;

        //查看map中是否存在，如果存在就从map中取，不用再计算了
        if (map.containsKey(i - 2))
            lastLast = map.get(i - 2);
        else {
            //偷上上家之前所能得到的最大值
            lastLast = robHelper(nums, i - 2, map);
            //如果map中不存在就计算，计算完之后要存储在map中，下次用的
            //时候直接从map中取，不用再计算了。
            map.put(i - 2, lastLast);
        }

        //原理同时
        if (map.containsKey(i - 1))
            last = map.get(i - 1);
        else {
            //偷上家之前所能得到的最大值
            last = robHelper(nums, i - 1, map);
            map.put(i - 1, last);
        }

        //偷上上家之前的还可以再偷当前这一家
        int cur = lastLast + nums[i];
        //然后返回偷当前这一家和不偷当前这一家的最大值
        return Math.max(last, cur);
    }

}
