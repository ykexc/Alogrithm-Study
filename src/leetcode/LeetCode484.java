package leetcode;

/**
 * 贪心构造，类似2375，942
 */
public class LeetCode484 {

    public int[] findPermutation(String s) {
        var n = s.length();
        char[] chars = s.toCharArray();
        var ans = new int[n + 1];
        for (int i = 0; i <= n; i++) ans[i] = i + 1;
        for (int i = 0, j = 0; i < n; i++) {
            while (i < n && chars[i] == 'I') i++;
            j = i;
            while (i < n && chars[i] == 'D') i++;
            swap(ans, j, i);
        }
        return ans;
    }

    void swap(int[] arr, int i, int j) {
        while (i < j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
            i++;
            j--;
        }
    }
}
