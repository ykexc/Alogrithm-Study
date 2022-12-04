package leetcode;

import java.util.Arrays;

/**
 * 01背包,回溯,二进制枚举，三进制枚举
 */
public class LC1774 {

    /**
     * 二进制枚举无剪枝
     *
     * @param baseCosts
     * @param toppingCosts
     * @param target
     * @return
     */
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        var ans = Integer.MAX_VALUE;
        var n = toppingCosts.length;
        for (var baseCost : baseCosts) {
            var t = baseCost;
            if (t == target) return t;
            if (Math.abs(t - target) < Math.abs(ans - target)) ans = t;
            else if (Math.abs(t - target) == Math.abs(ans - target)) {
                ans = Math.min(ans, t);
            }
            for (int i = 0; i < 1 << n; i++) {
                for (int j = 0; j < 1 << n; j++) {
                    var te = t;
                    for (int k = 0; k < n; k++) {
                        if ((i >> k & 1) == 1) {
                            te += toppingCosts[k];
                        }
                        if ((j >> k & 1) == 1) {
                            te += toppingCosts[k];
                        }
                        if (te == target) return te;
                        if (Math.abs(te - target) < Math.abs(ans - target)) ans = te;
                        else if (Math.abs(te - target) == Math.abs(ans - target)) {
                            ans = Math.min(ans, te);
                        }
                    }
                }
            }
        }
        return ans;
    }

    int res;

    public int closestCost2(int[] baseCosts, int[] toppingCosts, int target) {
        res = Arrays.stream(baseCosts).min().getAsInt();
        for (int b : baseCosts) {
            dfs(toppingCosts, 0, b, target);
        }
        return res;
    }

    public void dfs(int[] toppingCosts, int p, int curCost, int target) {
        if (Math.abs(res - target) < curCost - target) {
            return;
        } else if (Math.abs(res - target) >= Math.abs(curCost - target)) {
            if (Math.abs(res - target) > Math.abs(curCost - target)) {
                res = curCost;
            } else {
                res = Math.min(res, curCost);
            }
        }
        if (p == toppingCosts.length) {
            return;
        }
        dfs(toppingCosts, p + 1, curCost + toppingCosts[p] * 2, target);
        dfs(toppingCosts, p + 1, curCost + toppingCosts[p], target);
        dfs(toppingCosts, p + 1, curCost, target);
    }

    public int closestCost3(int[] baseCosts, int[] toppingCosts, int target) {
        int x = Arrays.stream(baseCosts).min().getAsInt();
        if (x >= target) {
            return x;
        }
        boolean[] can = new boolean[target + 1];
        int res = 2 * target - x;
        for (int b : baseCosts) {
            if (b <= target) {
                can[b] = true;
            } else {
                res = Math.min(res, b);
            }
        }
        for (int t : toppingCosts) {
            for (int count = 0; count < 2; ++count) {
                for (int i = target; i > 0; --i) {
                    if (can[i] && i + t > target) {
                        res = Math.min(res, i + t);
                    }
                    if (i - t > 0) {
                        can[i] = can[i] | can[i - t];
                    }
                }
            }
        }
        for (int i = 0; i <= res - target; ++i) {
            if (can[target - i]) {
                return target - i;
            }
        }
        return res;
    }

    /**
     * 三进制枚举
     *
     * @param baseCosts
     * @param toppingCosts
     * @param target
     * @return
     */
    public int closestCost4(int[] baseCosts, int[] toppingCosts, int target) {
        var n = toppingCosts.length;
        int k = (int) Math.pow(3, n);
        var ans = Integer.MAX_VALUE;
        for (var baseCost : baseCosts) {
            for (int i = 0; i < n; i++) {
                int t = baseCost;
                for (int j = 0, l = i; j < n; j++) {
                    t += (l % 3) * toppingCosts[j];
                    l /= 3;
                }
                if (t == target) return target;
                if ((Math.abs(t - target) < Math.abs(ans - target)) || (Math.abs(t - target) == Math.abs(ans - target) && ans > t)) {
                    ans = t;
                }
            }
        }
        return ans;
    }

}
