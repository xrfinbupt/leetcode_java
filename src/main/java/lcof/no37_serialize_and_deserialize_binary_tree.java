package lcof;

import common.TreeNode;

import java.util.ArrayDeque;

/**
 * 剑指 Offer 37. 序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 示例: 
 *
 * 你可以将以下二叉树：
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 */
public class no37_serialize_and_deserialize_binary_tree {
    // Encodes a tree to a single string.

    /**
     * 执行用时：21 ms, 在所有 Java 提交中击败了68.55%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了82.02%的用户
     */
    public String serialize(TreeNode root) {
        if(root == null) return "";

        StringBuilder strBuilder = new StringBuilder();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while(queue.size()>0){
            TreeNode node = queue.removeFirst();
            if(strBuilder.length()!=0) strBuilder.append(",");
            if(node.val == Integer.MAX_VALUE){
                strBuilder.append("null");
                continue;
            }else {
                strBuilder.append(node.val);
            }

            if(node.left!=null){
                queue.addLast(node.left);
            }else{
                queue.addLast(new TreeNode(Integer.MAX_VALUE));
            }

            if(node.right!=null){
                queue.addLast(node.right);
            }else{
                queue.addLast(new TreeNode(Integer.MAX_VALUE));
            }
        }
        return strBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) return null;
        String [] strArray = data.split(",");
        int len = strArray.length;
        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(strArray[0]));
        queue.add(root);
        int index = 1;
        while(queue.size()>0){
            TreeNode node = queue.removeFirst();
            String leftStr = strArray[index++];
            if(!leftStr.equals("null")){
                node.left = new TreeNode(Integer.parseInt(leftStr));
                queue.addLast(node.left);
            }
            String rightStr = strArray[index++];
            if(!rightStr.equals("null")){
                node.right = new TreeNode(Integer.parseInt(rightStr));
                queue.addLast(node.right);
            }
        }

        return root;
    }
}
