package lcof_offer;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 *
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * 示例:
 * 给定如下二叉树，以及目标和 target = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *  
 * 提示：
 * 节点总数 <= 10000
 * 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 */
public class offer34_pathSum {
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 递归 + 回溯法
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了64.56%的用户
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if(root==null) return result;

        List<Integer> datas = new ArrayList<>();
        recursive_pathSum(root,target,datas);

        return result;
    }
    public void recursive_pathSum(TreeNode root, int target,List<Integer> datas) {
        if (root == null) return;

        int val = root.val;
        datas.add(val);

        if (root.left != null) {
            recursive_pathSum(root.left, target - val, datas);
        }
        if (root.right != null) {
            recursive_pathSum(root.right, target - val, datas);
        }
        if (root.left == null && root.right == null && val == target) {
            result.add(new ArrayList<>(datas));
        }
        datas.remove(datas.size() - 1);

        return;
    }
}
