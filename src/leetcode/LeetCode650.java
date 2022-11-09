package leetcode;

public class LeetCode650 {

    int ans = 0x3f3f3f3f;
    int _n;
    public int minSteps(int n) {
        _n = n;
        dfs(1,0,0,false);
        return ans;
    }
    void dfs(int x, int p, int c, boolean pre) {
        if (x >= _n) {
            if (x == _n) ans = Math.min(ans, c);
            return;
        }
        if (p == 0) {
            dfs(x, x, c + 1,true);
        }
        else if (!pre) {
            dfs(x + p, p, c + 1, pre);
            dfs(x, x, c + 1, !pre);
        } else {
            dfs(x + p, p, c + 1, !pre);
        }
    }

}
