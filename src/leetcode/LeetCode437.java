package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 树的前缀和
 */
public class LeetCode437 {

    Map<Long, Integer> map = new HashMap<>();
    int ans, t;
    public int pathSum(TreeNode root, int _t) {
        if (root == null) return 0;
        t = _t;
        map.put(0L, 1);
        dfs(root, root.val);
        return ans;
    }
    void dfs(TreeNode root, long val) {
        if (map.containsKey(val - t)) ans += map.get(val - t);
        map.put(val, map.getOrDefault(val, 0) + 1);
        if (root.left != null) dfs(root.left, val + root.left.val);
        if (root.right != null) dfs(root.right, val + root.right.val);
        map.put(val, map.getOrDefault(val, 0) - 1);
    }


}
