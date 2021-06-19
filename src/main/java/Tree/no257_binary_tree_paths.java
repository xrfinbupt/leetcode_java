package Tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 */
public class no257_binary_tree_paths {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了78.87%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了92.85%的用户
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        recursive_binaryTreePaths(root, new StringBuilder(), result);

        return result;
    }

    public void recursive_binaryTreePaths(TreeNode root, StringBuilder strBuilder, List<String> result) {
        if (root == null) return;
        int val = root.val;
        strBuilder.append(val);
        if (root.left != null) {
            StringBuilder newStrBuilder = new StringBuilder(strBuilder);
            newStrBuilder.append("->");
            recursive_binaryTreePaths(root.left, newStrBuilder, result);
        }
        if (root.right != null) {
            StringBuilder newStrBuilder = new StringBuilder(strBuilder);
            newStrBuilder.append("->");
            recursive_binaryTreePaths(root.right, newStrBuilder, result);
        }

        if (root.left == null && root.right == null) {
            result.add(strBuilder.toString());
        }
        return;
    }
}
