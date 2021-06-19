package Tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *  
 * 提示：
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 */
public class no113_path_sum_ii {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了69.39%的用户
     */
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return result;
        List<Integer> datas = new ArrayList<>();
        recursive_pathSum(root, targetSum, datas);

        return result;
    }

    public void recursive_pathSum(TreeNode root, int targetSum, List<Integer> datas) {
        if (root == null) return;
        int val = root.val;
        datas.add(val);
        if (root.left != null) {
            recursive_pathSum(root.left, targetSum - val, datas);
        }
        if (root.right != null) {
            recursive_pathSum(root.right, targetSum - val, datas);
        }
        if (root.left == null && root.right == null && targetSum == val) {
            result.add(new ArrayList<>(datas));
        }

        datas.remove(datas.size() - 1);
    }
}
