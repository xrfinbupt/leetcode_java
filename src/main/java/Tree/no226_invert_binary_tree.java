package Tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 */
public class no226_invert_binary_tree {
    /**
     * 方法1 递归
     *
     * @param root
     * @return
     */
    public TreeNode invertTree_method1(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;

        recursive(root);

        return root;
    }

    public void recursive(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null && right == null) return;

        root.left = right;
        root.right = left;
        if (left != null)
            recursive(left);
        if (right != null)
            recursive(right);
    }

    /**
     * 方法2 非递归
     *
     * @param root
     * @return
     */
    public TreeNode invertTree_method2(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode temp = queue.poll();
            TreeNode left = temp.left;
            TreeNode right = temp.right;
            if (left == null && right == null) continue;

            temp.left = right;
            temp.right = left;
            if (left != null)
                queue.add(left);
            if (right != null)
                queue.add(right);
        }

        return root;
    }

}
