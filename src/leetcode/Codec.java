package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Codec {
    public String serialize(TreeNode root) {
        StringJoiner stringJoiner = new StringJoiner(",", "{", "}");
        StringBuilder sb = new StringBuilder(); // 利用StringBuilder拼接
        return toStr(root, sb).toString();
    }
    private StringBuilder toStr(TreeNode node, StringBuilder sb){ // 序列化dfs
        if(node == null) sb.append("null,");
        else{
            sb.append(node.val).append(",");
            sb = toStr(node.left, sb);
            sb = toStr(node.right, sb);
        }
        return sb;
    }
    public TreeNode deserialize(String data) {
        String[] strNodes = data.split(",");
        List<String> nodes = new LinkedList<>(Arrays.asList(strNodes)); // 涉及头节点的操作，用LinkedList效率高
        return toTree(nodes);
    }
    private TreeNode toTree(List<String> nodes){ // 反序列化dfs
        if(nodes.get(0).equals("null")){
            nodes.remove(0); // 对此结点（null）完成反序列化，及时从nodes中去除
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes.get(0)));
        nodes.remove(0); // 对此结点完成反序列化，及时从nodes中去除
        root.left = toTree(nodes);
        root.right = toTree(nodes);
        return root;
    }
}