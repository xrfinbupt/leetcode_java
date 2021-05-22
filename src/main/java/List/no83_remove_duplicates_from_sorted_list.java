package List;

import common.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
public class no83_remove_duplicates_from_sorted_list {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next == null) return head;

        int pre = head.val;
        ListNode preNode = head;
        ListNode pNode = head;
        while(pNode !=null && pNode.next!=null){
            ListNode currNode = pNode.next;
            int val = currNode.val;

            if(pre == val) pNode = currNode;
            else{
                preNode.next = currNode;

                preNode = currNode;
                pNode = currNode;
                pre = val;
            }
        }
        preNode.next = null;

        return head;
    }
}
