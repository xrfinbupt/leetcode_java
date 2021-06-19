package Tree;

import common.TreeNode;

/**
 * 112. 路径总和
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 *
 * 提示：
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/path-sum
 */
public class no112_path_sum {
    boolean result = false;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了98.19%的用户
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return result;
        recursive_hasPathSum(root,targetSum);

        return result;
    }
    public void recursive_hasPathSum(TreeNode root,int targetSum){
        if(root == null || result) return;

        int val = root.val;
        if(root.left!=null) recursive_hasPathSum(root.left,targetSum-val);
        if(result) return;

        if(root.right!=null) recursive_hasPathSum(root.right,targetSum-val);
        if(result) return;

        if(root.left==null && root.right==null && val==targetSum){
            result = true;
        }
    }

}
