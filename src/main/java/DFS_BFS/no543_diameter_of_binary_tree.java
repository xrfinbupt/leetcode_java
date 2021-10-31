package DFS_BFS;

import common.TreeNode;

/**
 * 543. 二叉树的直径
 * <p>
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -100 <= Node.val <= 100
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * <p>
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 */
public class no543_diameter_of_binary_tree {
    int len = 0;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了88.75%的用户
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs_diameterOfBinaryTree(root);
        return len;
    }

    public int dfs_diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lLen = 0;
        if (root.left != null) {
            lLen = 1 + dfs_diameterOfBinaryTree(root.left);
        }
        int rLen = 0;
        if (root.right != null) {
            rLen = 1 + dfs_diameterOfBinaryTree(root.right);
        }

        len = Math.max(len, lLen + rLen);
        return Math.max(lLen, rLen);
    }
}
