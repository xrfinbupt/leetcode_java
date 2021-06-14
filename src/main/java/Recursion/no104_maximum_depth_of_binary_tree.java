package Recursion;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/description/
 * https://github.com/azl397985856/leetcode/blob/master/problems/104.maximum-depth-of-binary-tree.md
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3
 */
public class no104_maximum_depth_of_binary_tree {
    private int maxDeep = 0;

    /**
     * 方法1 递归
     *
     * @param root
     * @return
     */
    public int maxDepth_method1(TreeNode root) {
        if (root == null) return 0;

        recursive(root, 1);
        return maxDeep;
    }

    public void recursive(TreeNode root, int deep) {
        if (root == null) return;
        maxDeep = Math.max(maxDeep, deep);
        if (root.left != null)
            recursive(root.left, deep + 1);
        if (root.right != null)
            recursive(root.right, deep + 1);
        return;
    }

    /**
     * 方法1 迭代
     * @param root
     * @return
     */
    public int maxDepth_method2(TreeNode root) {
        if (root == null) return 0;

        int deep = 0;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root);

        while (!queue1.isEmpty()) {
            TreeNode temp = queue1.poll();
            if (temp.left != null) queue2.add(temp.left);
            if (temp.right != null) queue2.add(temp.right);

            if (queue1.isEmpty()) {
                deep++;
                Queue<TreeNode> tempQueue = queue2;
                queue2 = queue1;
                queue1 = tempQueue;
            }
        }

        return deep;
    }
}
