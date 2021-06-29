package Tree;

import common.TreeNode;

/**
 * 111. 二叉树的最小深度 (easy)
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *  
 * 提示：
 * 树中节点数的范围在 [0, 10^5] 内
 * -1000 <= Node.val <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 */
public class no111_minimum_depth_of_binary_tree {
    int minDeep = Integer.MAX_VALUE;

    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了71.66%的用户
     * 内存消耗：58.5 MB, 在所有 Java 提交中击败了70.73%的用户
     */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;

        minDepth(root, 1);
        return minDeep;
    }

    public void minDepth(TreeNode root, int deep) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            minDeep = Math.min(minDeep, deep);
        }
        minDepth(root.left, deep + 1);
        minDepth(root.right, deep + 1);
    }
}
