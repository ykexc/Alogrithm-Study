package leetcode;

/**
 * 贪心构造,类似还有942，484
 */
public class LeetCode2375 {
    public String smallestNumber(String pattern) {
        var n = pattern.length();
        var chars = new char[n + 1];
        for (int i = 0; i <= n; i++) {
            chars[i] = (char) ('1' + i);
        }
        for (int i = 0, j = 0; i < n; ) {
            while (i < n && pattern.charAt(i) == 'I') i++;
            j = i;
            while (i < n && pattern.charAt(i) == 'D') i++;
            reverse(chars, j, i);
        }
        return new String(chars);
    }

    void reverse(char[] arr, int a, int b) {
        while (a < b) {
            arr[a] ^= arr[b];
            arr[b] ^= arr[a];
            arr[a] ^= arr[b];
            a++;
            b--;
        }
    }
}
