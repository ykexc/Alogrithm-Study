package leetcode;

/**
 * @author 86152
 */
public class LeetCode779 {

    /**
     * 倒推递归
     * @param n
     * @param k
     * @return
     */
    public int kthGrammar(int n, int k) {
        return dfs(n, k, 1) == 0 ? 1 : 0;
    }
    int dfs(int r, int l, int v) {
        if (r == 1) {
            return v;
        }
        if (l % 2 == 0 && v == 0 || l % 2 == 1 && v == 1) {
            return dfs(r - 1,(l + 1) / 2, 1);
        }
        return dfs(r - 1, (l + 1) / 2, 0);
    }



}
