package leetcode;

/**
 * @author 86152
 */


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class LeetCode998 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {//递归
        if (root.val < val) {
            return new TreeNode(val, root, null);
        }
        dfs(root, val, root.right);
        return root;
    }

    public void dfs(TreeNode t, int val, TreeNode r) {
        if (r == null || r.val < val) {
            t.right = new TreeNode(val, r, null);
            return;
        }
        dfs(r, val, r.right);
    }
    public TreeNode answer(TreeNode root, int val) {//迭代
        TreeNode prev = null;
        TreeNode t = root;
        while (t != null && t.val > val) {
            prev = t; t = t.right;
        }
        if (prev == null) {
            return new TreeNode(val,t,null);
        }
        prev.right = new TreeNode(val,t,null);
        return root;
    }
}
