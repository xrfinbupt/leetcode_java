package Tree;

import common.TreeNode;

/**
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 * 输入：root = []
 * 输出：true
 *  
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -10^4 <= Node.val <= 10^4
 *
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 */
public class no110_isBalanced {
    boolean result = true;

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.99%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了13.48%的用户
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return result;
        dfs(root, 0);
        return result;
    }

    private int dfs(TreeNode root, int level) {
        if (root == null || !result) return level;

        int deep1 = dfs(root.left, level + 1);
        int deep2 = dfs(root.right, level + 1);
        if (Math.abs(deep1 - deep2) > 1) {
            result = false;
        }
        return Math.max(deep1, deep2);
    }
}
