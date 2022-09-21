package leetcode;

/**
 * @author 86152
 */
public class LeetCode854 {
    int ans;

    public int kSimilarity(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }
        var sb1 = new StringBuilder();
        var sb2 = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            sb1.append(s1.charAt(i));
            sb2.append(s2.charAt(i));
        }
        ans = s1.length() - 1;
        dfs(s1.toString(), s2.toString(), 0, 0);
        return ans;
    }

    public void dfs(String s1, String s2, int index, int c) {
        if (c > ans) return;
        while (index < s1.length() && s1.charAt(index) == s2.charAt(index)) {
            index++;
        }
        if (index == s1.length()) {
            ans = Math.min(ans, c);
            return;
        }
        int t = 0;
        for (int i = index; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                t++;
            }
        }
        int x = (t + 1) / 2 + c;
        if (x >= ans) {
            return;
        }
        for (int i = index + 1; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(index)) {
                String s = swap(s1, index, i);
                dfs(s, s2, index + 1, c + 1);
            }
        }


    }

    public String swap(String s1, int a, int b) {
        char[] arr = s1.toCharArray();
        char c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
        return new String(arr);
    }
}
