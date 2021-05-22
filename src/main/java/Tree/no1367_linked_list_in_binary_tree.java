package Tree;

import common.ListNode;
import common.TreeNode;

/**
 * 1367. 二叉树中的列表
 *
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 *
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 *
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 *
 * 示例 1：
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 *
 * 示例 2：
 * 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 *
 * 示例 3：
 * 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：false
 * 解释：二叉树中不存在一一对应链表的路径。
 *
 * 提示：
 * 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 链表包含的节点数目在 1 到 100 之间。
 * 二叉树包含的节点数目在 1 到 2500 之间。
 *
 * 链接：https://leetcode-cn.com/problems/linked-list-in-binary-tree
 *
 */
public class no1367_linked_list_in_binary_tree {
    boolean result = false;

    /**
     * 这种方式 为什么不行呢?
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
//        if (head.val == root.val) sub_isSubPath(head, root, head);
//        else sub_isSubPath(head, root, null);

        sub_isSubPath(head, root, null);
        if(root.left!=null)sub_isSubPath(head, root.left, null);
        if(root.right!=null)sub_isSubPath(head, root.right, null);

        return result;
    }

    public void sub_isSubPath(ListNode head, TreeNode root,ListNode p) {
        if(result) return;

        if (p == null || p != null && root.val != p.val) {
            if (head.val == root.val) sub_isSubPath(head, root, head);
            else {
                if (root.left != null) sub_isSubPath(head, root.left, null);
                if (root.right != null) sub_isSubPath(head, root.right, null);
            }
        } else if (p != null && root.val == p.val) {
            if (p.next == null) {
                result = true;
                return;
            }

            if (root.left != null) {
                sub_isSubPath(head, root.left, p.next);
                //if(root.left.val == head.val) sub_isSubPath(head, root.left, head);
            }
            if (root.right != null) {
                sub_isSubPath(head, root.right, p.next);
                //if(root.right.val == head.val) sub_isSubPath(head, root.right, head);
            }
        }
    }

    /**
     * 参考解答
     * https://leetcode-cn.com/problems/linked-list-in-binary-tree/solution/java-shen-du-you-xian-sou-suo-zhu-xing-z-378y/
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath2(ListNode head, TreeNode root) {
        if (root == null) return false;
        return dfs(head, root) || isSubPath2(head, root.left) || isSubPath2(head, root.right);
    }

    public boolean dfs(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (head.val != root.val) return false;
        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }
}
