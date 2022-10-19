package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 86152
 */
public class LeetCode39 {


    List<List<Integer>> lis;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        lis = new ArrayList<>();
        back(candidates, target, new ArrayList<>(), 0);
        return lis;
    }

    public void back(int[] candidates, int target, List<Integer> list, int sum) {
        if (target == 0) {
            lis.add(new ArrayList<>(list));
            return;
        }
        for (int i = sum; i < candidates.length; i++) {
            if (candidates[i] > target) return;
            list.add(candidates[i]);
            back(candidates, target - candidates[i], list, i);
            list.remove(i);
        }
    }
}
