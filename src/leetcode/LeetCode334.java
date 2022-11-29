package leetcode;

public class LeetCode334 {

    public boolean increasingTriplet(int[] nums) {
        var n = nums.length;
        var stack = new int[n];
        var index = -1;
        for (int num : nums) {
            if (index == -1 || num > stack[index]) {
                stack[++index] = num;
            } else {
                var l = 0;
                var r = index;
                while (l < r) {
                    var mid = l + r >> 1;
                    if (stack[mid] >= num) {
                        r = mid;
                    } else l = mid + 1;
                }
                stack[l] = num;
            }
        }
        return index >= 2;
    }

}
