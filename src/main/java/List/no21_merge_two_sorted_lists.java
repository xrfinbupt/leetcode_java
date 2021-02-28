package List;

import common.ListNode;

// https://leetcode-cn.com/problems/merge-two-sorted-lists
public class no21_merge_two_sorted_lists {

    // 新链表是通过拼接给定的两个链表的所有节点组成的
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = null;
        ListNode pre = null;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                pre.next = l2;
                pre = pre.next;
                l2 = l2.next;
            } else if (l2 == null) {
                pre.next = l1;
                pre = pre.next;
                l1 = l1.next;
            } else if (l1.val <= l2.val) {
                if (head == null) {
                    head = l1;
                    pre = head;
                } else {
                    pre.next = l1;

                    pre = pre.next;
                }

                l1 = l1.next;
            } else {
                if (head == null) {
                    head = l2;
                    pre = head;
                } else {
                    pre.next = l2;

                    pre = pre.next;
                }
                l2 = l2.next;
            }
        }

        return head;
    }

    public static void main(String args[]) {
        no21_merge_two_sorted_lists obj = new no21_merge_two_sorted_lists();
        ListNode root1 = new ListNode(1);
        ListNode p = root1;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(4);

        ListNode root2 = new ListNode(1);
        p = root2;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);

        obj.mergeTwoLists(root1, root2);
    }
}
