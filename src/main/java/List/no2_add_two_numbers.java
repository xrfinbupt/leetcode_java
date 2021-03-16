package List;

import common.ListNode;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class no2_add_two_numbers {
    /**
     * 返回的链表直接new的，应该可以利用参数的空间
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int preFlag = 0;
        ListNode ans = null, p = null;

        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            int temp = p1.val + p2.val + preFlag;
            if (temp > 9) {
                preFlag = 1;
                temp -= 10;
            }else{
                preFlag = 0;
            }
            if (ans == null) {
                p = new ListNode(temp);
                ans = p;
            } else {
                p.next = new ListNode(temp);
                p = p.next;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        while (p1 != null) {
            int temp = p1.val + preFlag;
            if (temp > 9) {
                preFlag = 1;
                temp -= 10;
            }else{
                preFlag = 0;
            }
            p.next = new ListNode(temp);
            p = p.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            int temp = p2.val + preFlag;
            if (temp > 9) {
                preFlag = 1;
                temp -= 10;
            }else{
                preFlag = 0;
            }
            p.next = new ListNode(temp);
            p = p.next;
            p2 = p2.next;
        }

        if(preFlag>0){
            p.next = new ListNode(preFlag);
        }
        return ans;
    }
}
