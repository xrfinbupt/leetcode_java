package Tree;


import common.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 *
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 *
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *
 * 提示：
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 *  
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 *
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 */
public class no230_kth_smallest_element_in_a_bst {
    int result = -1;
    int index = 0;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了80.42%的用户
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        sub_kthSmallest(root,k);

        return result;
    }

    public void sub_kthSmallest(TreeNode root,int k) {
        if (root == null) return;

        sub_kthSmallest(root.left, k);
        index++;
        if (index == k && result == -1) {
            result = root.val;
        }
        if (result >= 0) return;
        sub_kthSmallest(root.right, k);
    }
}
