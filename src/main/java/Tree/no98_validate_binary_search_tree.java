package Tree;

import common.TreeNode;

/**
 * 98. 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class no98_validate_binary_search_tree {
    Integer pre;
    boolean flag = true;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了19.68%的用户
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        dfs(root);
        return flag;
    }
    public void dfs(TreeNode root) {
        if (root == null) return ;
        if(!flag) return;

         dfs(root.left);
         if(pre !=null && pre >= root.val){
             flag = false;
             return;
         }
         pre = root.val;

         dfs(root.right);
    }
}
