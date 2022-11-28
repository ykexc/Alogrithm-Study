package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 爆搜，状压DP
 */
public class LeetCode562 {


    int ans = 0;
    List<Integer>[] lis;
    int n;

    public int countArrangement(int n) {
        this.n = n;
        lis = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            lis[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) lis[i].add(j);
            }
        }
        dfs(1, 0);
        return ans;
    }

    void dfs(int i, int v) {
        if (i == n + 1) {
            ans++;
            return;
        }
        for (var x : lis[i]) {
            if (((v >> x) & 1) == 0) {
                dfs(i + 1, v | 1 << x);
            }
        }
    }

    /**
     * 状压DP
     */
    public int countArrangement2(int n) {
        var mask = 1 << n;
        var dp = new int[n + 1][mask];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < mask; j++) {
                for (int k = 1; k <= n; k++) {
                    if ((j >> (k - 1) & 1) == 0) continue;
                    if (k % i != 0 && i % k != 0) continue;
                    dp[i][j] += dp[i - 1][j & ~(1 << k - 1)];
                }
            }
        }
        return dp[n][mask - 1];
    }

    int getCnt(int x) {
        int ans = 0;
        while (x != 0) {
            x -= (x & -x); // lowbit
            ans++;
        }
        return ans;
    }
    public int countArrangement3(int n) {
        int mask = 1 << n;
        int[] f = new int[mask];
        f[0] = 1;
        // 枚举所有的状态
        for (int state = 1; state < mask; state++) {
            // 计算 state 有多少个 1（也就是当前排序长度为多少）
            int cnt = getCnt(state);
            // 枚举最后一位数值为多少
            for (int i = 0; i < n; i++) {
                // 数值在 state 中必须是 1
                if (((state >> i) & 1) == 0) continue;
                // 数值（i + 1）和位置（cnt）之间满足任一整除关系
                if ((i + 1) % cnt != 0 && cnt % (i + 1) != 0) continue;
                // state & (~(1 << i)) 代表将 state 中所选数值的位置置零
                f[state] += f[state & (~(1 << i))];
            }
        }
        return f[mask - 1];
    }
}
