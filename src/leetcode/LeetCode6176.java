package leetcode;

/**
 * @author 86152
 */
public class LeetCode6176 {

    public int mostFrequentEven(int[] nums) {
        var MAX = (int) 1e5 + 5;
        var arr = new int[MAX];
        var n = 0;
        var ans = -1;
        for (int num : nums) {
            if ((num & 1) == 0) {
                arr[num]++;
            }
        }
        for (int i = 0; i < MAX; i++) {
            if (arr[i] > n) {
                ans = i;
                n = arr[i];
            }
        }
        return ans;
    }
}
