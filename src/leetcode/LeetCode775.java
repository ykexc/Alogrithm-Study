package leetcode;

import java.util.HashMap;
import java.util.TreeSet;

public class LeetCode775 {

    //树状数组朴素解法
    int[] Bit;

    public boolean isIdealPermutation(int[] nums) {
        var n = nums.length;
        Bit = new int[n + 1];
        var set = new TreeSet<Integer>();
        var p = 1;
        var map = new HashMap<Integer, Integer>();
        for (var num : nums) set.add(num);
        for (var i : set) map.put(i, p++);
        var l1 = 0;
        for (int i = n - 1; i >= 0; i--) {
            int r = map.get(nums[i]);
            l1 += query(r - 1);
            add(r);
        }
        var l2 = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) l2++;
        }
        return l1 == l2;
    }


    int lowbit(int a) {
        return a & -a;
    }

    void add(int a) {
        while (a < Bit.length) {
            Bit[a]++;
            a += lowbit(a);
        }
    }

    int query(int a) {
        var ans = 0;
        while (a > 0) {
            ans += Bit[a];
            a -= lowbit(a);
        }
        return ans;
    }

    //数学解法
    public boolean isIdealPermutation2(int[] nums) {
        int max = nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < max) return false;
            max = Math.max(max, nums[i - 1]);//判断是否存在非局部最优的全局最优
        }
        return true;
    }

}
