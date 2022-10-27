package leetcode;

import java.util.*;

public class LeetCode40 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list =new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,target,0);
        return result;
    }

    private void dfs(int[] candidates, int target, int start) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            int one = candidates[i];
            list.add(one);
            dfs(candidates,target-one,i+1);
            list.remove(list.size()-1);
            //关键：递归后去除后续重复数字
            while (i < candidates.length -1 && candidates[i] == candidates[i+1]) ++i;
        }
    }
}
