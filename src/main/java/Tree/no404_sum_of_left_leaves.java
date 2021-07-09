package Tree;

import common.TreeNode;


public class no404_sum_of_left_leaves {
    private int result = 0;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了69.01%的用户
     */
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root,0);
        return result;
    }
    private boolean dfs(TreeNode root,int level) {
        if (root == null) return false;

        boolean left = dfs(root.left, level + 1);
        dfs(root.right, level + 1);

        if (root.left == null && root.right == null) {
            return true;
        }

        if (left) {
            result += root.left.val;
        }

        return false;
    }
}
