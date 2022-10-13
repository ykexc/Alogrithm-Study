package leetcode;

/**
 * @author 86152
 */
public class LeetCode769 {

    public int maxChunksToSorted(int[] arr) {
        var ans = 0;
        var begin = 0;
        var sum = 0;
        for (var i = 0; i < arr.length; i++) {
            sum += arr[i];
            int n = i - begin + 1;
            if (begin * n + (n - 1) * n / 2 == sum) {
                ans++;
                sum = 0;
                begin = i + 1;
            }
        }
        return ans;
    }

}
