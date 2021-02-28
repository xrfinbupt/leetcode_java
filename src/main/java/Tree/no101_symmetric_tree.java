package Tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * https://github.com/azl397985856/leetcode/blob/master/problems/101.symmetric-tree.md
 * https://leetcode-cn.com/problems/symmetric-tree/
 * <p>
 * <p>
 * <p>
 * 我能想到的思路:
 * 1 递归（copy一个镜像的Tree 然后遍历一遍 看是否一样）
 * 2 非递归(层序遍历 然后每一层元素 查看是否满足镜像)
 */
public class no101_symmetric_tree {
    /**
     * 方法1 递归
     *
     * @param root
     * @return
     */
    public boolean isSymmetric_method1(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return true;
        if (root.left != null && root.right != null) {
            return recursive(root.left, root.right);
        }
        return false;
    }

    private boolean recursive(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        if (l.val != r.val) return false;

        return recursive(l.left, r.right) && recursive(l.right, r.left);
    }

    /**
     * 方法2 非递归
     *
     * @param root
     * @return
     */
    public boolean isSymmetric_method2(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            TreeNode p1 = queue.poll();
            TreeNode p2 = queue.poll();
            if (p1 == null && p2 == null) return true;
            if (p1 == null || p2 == null) return false;
            if (p1.val != p2.val) return false;

            if (p1.left != null && p2.right != null) {
                queue.add(p1.left);
                queue.add(p2.right);
            } else if (p1.left != null || p2.right != null) {
                return false;
            }

            if (p1.right != null && p2.left != null) {
                queue.add(p1.right);
                queue.add(p2.left);
            } else if (p1.right != null || p2.left != null) {
                return false;
            }
        }
        return true;
    }

}
