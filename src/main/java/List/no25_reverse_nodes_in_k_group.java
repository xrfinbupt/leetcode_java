package List;

import common.ListNode;

// https://github.com/azl397985856/leetcode/blob/master/problems/25.reverse-nodes-in-k-groups.md
public class no25_reverse_nodes_in_k_group {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;

        ListNode dummp = new ListNode(0);
        dummp.next = head;
        // 四点法
        ListNode curr = head; // p4
        ListNode p2 = head; // p2
        ListNode p1 = dummp;// p1

        int index = 0;
        int count = 0;
        while (curr != null) {
            if (index != 0 && index % k == 0) {
                ListNode pre1 = p2;
                ListNode curr1 = p2.next;
                while (curr1 != curr) {
                    ListNode temp = curr1.next;
                    curr1.next = pre1;
                    pre1 = curr1;
                    curr1 = temp;
                }
                p1.next = pre1;
                p2.next = curr;
                p1 = p2;
                p2 = curr;

                count = 1;
            } else {
                count++;
            }

            curr = curr.next;
            index++;
        }

        // 边界处理
        if (count == k) {
            ListNode pre1 = p2;
            ListNode curr1 = p2.next;
            while (curr1 != null) {
                ListNode temp = curr1.next;
                curr1.next = pre1;

                pre1 = curr1;
                curr1 = temp;
            }
            p1.next = pre1;
            p2.next = null;
        }
        return dummp.next;
    }

    public static void main(String args[]) {
        no25_reverse_nodes_in_k_group obj = new no25_reverse_nodes_in_k_group();
        ListNode root = new ListNode(1);
        ListNode p = root;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        p = p.next;
        //p.next = new ListNode(5);
        //p = p.next;

        obj.reverseKGroup(root, 2);
    }
}
