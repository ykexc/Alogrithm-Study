package leetcode;

/**
 * @author 86152
 */
public class LeetCode687 {
    int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int l = 0;
        int r = 0;
        if (root.left != null && root.left.val == root.val) {
            l = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            r = right + 1;
        }
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, root.val), right = dfs(root.right, root.val);
        ans = Math.max(ans, left + right);
        return root.val == val ? Math.max(left, right) + 1 : 0;
    }
}
