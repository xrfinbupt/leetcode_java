package lcof_offer;

import common.ListNode;

/**
 * 剑指 Offer 18. 删除链表的节点
 * <p>
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 * <p>
 * 注意：此题对比原题有改动
 * <p>
 * 示例 1:
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * <p>
 * 示例 2:
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * <p>
 * 说明：
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 * <p>
 * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof
 */
public class offer18_deleteNode {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了55.94%的用户
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode p = head;
        while (p != null) {
            if (p.val == val) {
                pre.next = p.next;
                break;
            }
            pre = p;
            p = p.next;
        }

        return dummy.next;
    }
}
