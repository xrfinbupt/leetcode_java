package Tree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 107. 二叉树的层序遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 */
public class no107_binary_tree_level_order_traversal_ii {
    private List<List<Integer>> result = new LinkedList<>();
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.31%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了50.69%的用户
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();

        LinkedList<List<Integer>> linkResult = new LinkedList<>();
        LinkedList<Integer> dataLinkResult = new LinkedList<>();
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (deque.size() > 0) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeFirst();
                dataLinkResult.add(node.val);
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            linkResult.addFirst(dataLinkResult);
            dataLinkResult = new LinkedList<>();
        }
        return linkResult;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.32%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了95.70%的用户
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        if(root == null) return new ArrayList<>();

        dfs(root,0);
        return result;
    }
    public void dfs(TreeNode root,int level) {
        if(root == null) return ;

        if(result.size() == level) {
            result.add(0,new ArrayList<>());
        }
        List<Integer> datas = result.get(result.size() -1 - level);// 这个地方很妙
        datas.add(root.val);

        dfs(root.left,level+1);
        dfs(root.right,level+1);

        return;
    }
}
