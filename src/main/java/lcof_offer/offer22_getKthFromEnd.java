package lcof_offer;

import common.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * <p>
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * <p>
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 */
public class offer22_getKthFromEnd {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了92.91%的用户
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (k <= 0 || head == null) return null;
        ListNode pre = head;
        ListNode curr = null;

        int temp = k;
        while (temp-- > 1) {
            pre = pre.next;
            if (pre == null) return null;
        }

        while (pre != null) {
            pre = pre.next;
            if (curr == null) {
                curr = head;
            } else curr = curr.next;
        }
        return curr;
    }
}
