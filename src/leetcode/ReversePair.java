package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ReversePair {


    int[] t;
    public int reversePairs(int[] nums) {
        t = new int[nums.length + 1];
        var set = new TreeSet<Integer>();
        for (int num : nums) set.add(num);
        var map = new HashMap<Integer, Integer>();
        int p = 1;
        for (int t : set) {
            map.put(t, p);
            p++;
        }
        var ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            modify(map.get(nums[i]));
            ans += sum(map.get(nums[i]) - 1);
        }
        return ans;
    }
    int lowbit(int a) {
        return a & (-a);
    }
    void modify(int a) {
        while(a < t.length) {
            t[a]++;
            a += lowbit(a);
        }
    }

    int sum(int a) {
        int res = 0;
        while (a >= 1) {
            res += t[a];
            a -= lowbit(a);
        }
        return res;
    }

}
