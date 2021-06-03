package Tree;

import common.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 *
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *  
 * 提示：
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 */
public class no124_binary_tree_maximum_path_sum {
    int result = -5000;

    /**
     * 失败了好几次
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了65.30%的用户
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        sub_maxPathSum(root);
        return result;
    }

    public int sub_maxPathSum(TreeNode root) {
        int val = root.val;
        int leftVal = -5000, rightVal = -5000;
        if (root.left != null) {
            leftVal = sub_maxPathSum(root.left);
        }
        if (root.right != null) {
            rightVal = sub_maxPathSum(root.right);
        }

        int includeRootValMax = Math.max(val, Math.max(leftVal + val, rightVal + val));
        int max = Math.max(leftVal, Math.max(includeRootValMax, Math.max(rightVal, val + leftVal + rightVal)));
        if (max > result) result = max;

        if (max == (leftVal + val + rightVal)) {
            return includeRootValMax;
        } else if (includeRootValMax >= 0) {
            return includeRootValMax;
        } else {
            return -5000;
        }
    }
}
