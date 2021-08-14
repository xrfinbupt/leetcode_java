package lcof_offer;

import common.TreeNode;

/**
 * 剑指 Offer 27. 二叉树的镜像
 *
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *  
 *
 * 限制：
 * 0 <= 节点个数 <= 1000
 * 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
 */
public class offer27_mirrorTree {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了37.60%的用户
     */
    public TreeNode mirrorTree(TreeNode root) {
        recursive_mirrorTree(root);

        return root;
    }

    private void recursive_mirrorTree(TreeNode root) {
        if(root==null) return;
        TreeNode left = root.left;
        if (left != null) recursive_mirrorTree(left);

        TreeNode right = root.right;
        if (right != null) recursive_mirrorTree(right);
        root.left = right;
        root.right = left;
    }
}
