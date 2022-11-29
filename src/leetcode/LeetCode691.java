package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 状压DP,记忆化搜索，BFS
 */

public class LeetCode691 {

    //记忆化搜索
    String[] _stickers;
    String _target;
    int l;
    int[] memory;

    public int minStickers3(String[] stickers, String target) {
        l = target.length();
        _stickers = stickers;
        _target = target;
        memory = new int[1 << l];
        Arrays.fill(memory, -1);
        var ans = dfs(0);
        return ans == 0x3f3f3f3f ? -1 : ans;
    }

    int dfs(int state) {
        if (state == (1 << l) - 1) {
            return 0;
        }
        if (memory[state] != -1) {
            return memory[state];
        }
        int ans = 0x3f3f3f3f;
        for (String sticker : _stickers) {
            int newState = state;
            for (char c : sticker.toCharArray()) {
                for (int i = 0; i < l; i++) {
                    if (_target.charAt(i) == c && ((newState >> i) & 1) == 0) {
                        newState |= 1 << i;
                        break;
                    }
                }
            }
            if (newState != state) {
                ans = Math.min(ans, dfs(newState) + 1);
            }
        }
        return memory[state] = ans;
    }

    //BFS
    public int minStickers2(String[] stickers, String target) {
        var queue = new ArrayDeque<int[]>();
        var n = target.length();
        queue.add(new int[]{0, 0});
        var tar = (1 << n) - 1;
        var set = new HashSet<Integer>();
        while (!queue.isEmpty()) {
            var state = queue.pollFirst();
            if (state[0] == tar) return state[1];
            for (String sticker : stickers) {
                int newState = state[0];
                for (char c : sticker.toCharArray()) {
                    for (int j = 0; j < n; j++) {
                        if (c == target.charAt(j) && (newState >> j & 1) == 0) {
                            newState |= 1 << j;
                            break;
                        }
                    }
                }
                if (newState != state[0] && !set.contains(newState)) {
                    queue.addLast(new int[]{newState, state[1] + 1});
                    set.add(newState);
                }
            }
        }
        return -1;
    }

    //状压DP
    public int minStickers(String[] stickers, String target) {
        var n = target.length();
        var dp = new int[1 << n];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for (var i = 0; i < (1 << n); i++) {
            if (dp[i] == 0x3f3f3f3f) continue;
            for (var sticker : stickers) {
                int x = i;
                for (var c : sticker.toCharArray()) {
                    for (var j = 0; j < n; j++) {
                        if (c == target.charAt(j) && ((x >> j & 1) == 0)) {
                            x |= 1 << j;
                            break;
                        }
                    }
                }
                dp[x] = Math.min(dp[x], dp[i] + 1);
            }
        }
        return dp[(1 << n) - 1] == 0x3f3f3f3f ? -1 : dp[(1 << n) - 1];
    }
}
