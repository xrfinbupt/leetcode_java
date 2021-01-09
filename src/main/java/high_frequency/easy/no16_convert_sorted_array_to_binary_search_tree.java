package high_frequency.easy;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * https://github.com/azl397985856/leetcode/blob/master/problems/108.convert-sorted-array-to-binary-search-tree.md
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class no16_convert_sorted_array_to_binary_search_tree {
    /**
     * 我自己想到的一种方法
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST_method1(int[] nums) {
        if (nums == null || nums.length == 0 ) return null;

        int len = nums.length;
        int mid = (len + 1) / 2;
        TreeNode root = new TreeNode(nums[mid - 1]);
        if (mid - 2 >= 0)
            recursive(root, nums, 0, mid - 2, true);

        if (len - 1 >= mid)
            recursive(root, nums, mid, len - 1, false);

        return root;
    }

    public void recursive(TreeNode root, int[] nums, int l, int r, boolean leftOrRight) {
        if (l > r) return;

        int mid = (l + r + 1) / 2;
        TreeNode temp = new TreeNode(nums[mid]);
        if (mid - 1 >= l)
            recursive(temp, nums, l, mid - 1, true);

        if (r >= mid + 1)
            recursive(temp, nums, mid + 1, r, false);

        if (leftOrRight) root.left = temp;
        else root.right = temp;
    }

    /**
     * 网上的一种方法
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST_method2(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int len = nums.length;
        int mid = (len + 1) / 2;
        TreeNode root = new TreeNode(nums[mid - 1]);
        if (mid - 2 >= 0)
            root.left = recursive2(nums, 0, mid - 2);

        if (len - 1 >= mid)
            root.right = recursive2(nums, mid, len - 1);

        return root;
    }

    public TreeNode recursive2(int[] nums, int l, int r) {
        if (l > r) return null;

        int mid = (l + r + 1) / 2;
        TreeNode temp = new TreeNode(nums[mid]);
        if (mid - 1 >= l)
            temp.left = recursive2(nums, l, mid - 1);

        if (r >= mid + 1)
            temp.right = recursive2(nums, mid + 1, r);

        return temp;
    }
}
