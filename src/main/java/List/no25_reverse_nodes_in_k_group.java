package List;

import common.ListNode;


/**
 * 25. K 个一组翻转链表
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *  
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 *
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 *
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
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
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了29.52%的用户
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if(head==null || head.next == null || k ==1) return head;
        ListNode root = null;
        ListNode pre = null;
        ListNode first = head;
        ListNode next = first;

        boolean flag = true;
        while(flag){
            for(int i=0;i<k;i++) {
                if(next==null){
                    flag = false;
                    break;
                }
                next  = next.next;
            }
            if(flag) {
                ListNode[] resultNode = reverse(pre, first, next);
                pre = resultNode[1];
                first = next;
                next = first;
                if (root == null) root = resultNode[0];
            }
        }

        return root;
    }

    private ListNode[] reverse(ListNode pre, ListNode first, ListNode next){
        ListNode lastNode = first;
        ListNode nextNode = next;
        while(first!=next){
            ListNode temp = first.next;
            first.next = nextNode;

            nextNode = first;
            first = temp;
        }
        if(pre!=null) pre.next = nextNode;
        return new ListNode[]{nextNode,lastNode};
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
