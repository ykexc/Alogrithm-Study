package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86152
 */
public class LeetCode652 {

    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> list = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return null;
        }
        serialize(root);
        return list;
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        String str = "" + root.val + "," + serialize(root.left) + "," + serialize(root.right);
        map.put(str, map.getOrDefault(str, 0) + 1);
        if ((map.get(str) ^ 2) == 0) {
            list.add(root);
        }
        return str;
    }
}
