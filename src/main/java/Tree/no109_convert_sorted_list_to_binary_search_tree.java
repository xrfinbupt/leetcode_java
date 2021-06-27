package Tree;

import common.ListNode;
import common.TreeNode;

/**
 * 109. 有序链表转换二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 */
public class no109_convert_sorted_list_to_binary_search_tree {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了57.77%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了99.03%的用户
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;

        ListNode pre = null;
        ListNode pmid = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p2 = p2.next.next;
            pre = pmid;
            pmid = pmid.next;
        }
        if(pre!=null) pre.next = null;

        TreeNode root = new TreeNode(pmid.val);
        if(pmid!=head) root.left = sortedListToBST(head);

        pmid = pmid.next;
        root.right = sortedListToBST(pmid);
        return root;
    }
}
