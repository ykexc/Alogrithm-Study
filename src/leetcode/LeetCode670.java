package leetcode;

/**
 * @author 86152
 */
public class LeetCode670 {

    public int maximumSwap(int num) {
        var arr = Integer.toString(num).toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            for (int k = arr.length - 1; k > i; k--) {
                if (arr[k] > arr[j]) {
                    j = k;
                }
            }
            if (j != i) {
                swap(arr, i, j);
                break;
            }
        }
        return Integer.parseInt(new String(arr));
    }

    public void swap(char[] arr, int a, int b) {
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }
}
