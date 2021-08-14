package lcof_offer;

import common.TreeNode;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * 限制：
 * 0 <= 树的结点个数 <= 10000
 * 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
 *
 * 链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof
 */
public class offer55_isBalanced {
    boolean result = true;

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了22.44%的用户
     */
    public boolean isBalanced(TreeNode root) {
        dfs(root,0);

        return result;
    }

    public int dfs(TreeNode root,int level){
        if(root == null) return level;
        if(!result) return level;

        TreeNode left = root.left;
        TreeNode right = root.right;

        int deep1 = dfs(left,level+1);
        int deep2 = dfs(right,level+1);
        if(Math.abs(deep1-deep2)>1){
            result = false;
        }
        return Math.max(deep1,deep2);
    }
}
