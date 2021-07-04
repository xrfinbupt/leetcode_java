package Tree;

import common.TreeNode;

import java.util.*;

/**
 * 145. 二叉树的后序遍历(Easy)
 *
 * 给定一个二叉树，返回它的 后序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 */
public class no145_binary_tree_postorder_traversal {
    private List<Integer> result = new ArrayList<>();

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了27.01%的用户
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return result;
        dfs(root);
        return result;
    }
    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        dfs(root.right);
        result.add(root.val);
    }

    /**
     * 非递归
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了52.11%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了18.43%的用户
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        if(root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        Set<TreeNode> nodeSet = new HashSet<>();
        queue.add(root);
        while(queue.size()>0){
            TreeNode node = queue.removeFirst();

            if (nodeSet.contains(node)) {
                result.add(node.val);
            } else {
                queue.addFirst(node);
                nodeSet.add(node);
                if (node.right != null) queue.addFirst(node.right);
                if (node.left != null) queue.addFirst(node.left);
            }
        }
        return result;
    }
}
