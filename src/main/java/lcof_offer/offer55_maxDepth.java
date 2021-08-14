package lcof_offer;

import common.TreeNode;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 *
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 提示：
 * 节点总数 <= 10000
 * 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 */
public class offer55_maxDepth {
    int deep = 0;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了73.58%的用户
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return deep;
        dfs(root,0);
        return deep;
    }
    private void dfs(TreeNode root,int currDeep){
        if(root == null) return;
        deep = Math.max(deep,currDeep+1);
        dfs(root.left,currDeep+1);
        dfs(root.right,currDeep+1);
    }
}
