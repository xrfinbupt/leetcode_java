package List;

import common.ListNode;

/**
 * 148. 排序链表
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *  
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 <= Node.val <= 10^5
 * <p>
 * 链接：https://leetcode-cn.com/problems/sort-list
 *
 * @author xurongfei
 * @Date 2021/11/7
 */
public class no148_sort_list {

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了76.87%的用户
     * 内存消耗：46.5 MB, 在所有 Java 提交中击败了72.30%的用户
     */
    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        if (len < 2) {
            return head;
        }

        p = getMidRightList(head, len);
        int leftLen = (len + 1) / 2;
        int rightLen = len - leftLen;

        return mergeSort(head, leftLen, p, rightLen);
    }

    private ListNode getMidRightList(ListNode head, int len) {
        int mid = (len + 1) / 2;
        ListNode p = head;
        ListNode pre = null;
        while (mid-- > 0) {
            pre = p;
            p = p.next;
        }
        pre.next = null;
        return p;
    }

    public ListNode mergeSort(ListNode leftList, int leftLen, ListNode rightList, int rightLen) {
        int leftLen1, rightLen1;
        ListNode nextList;
        if (leftLen > 1) {
            nextList = getMidRightList(leftList, leftLen);
            leftLen1 = (leftLen + 1) / 2;
            rightLen1 = leftLen - leftLen1;
            leftList = mergeSort(leftList, leftLen1, nextList, rightLen1);
        }

        if (rightLen > 1) {
            nextList = getMidRightList(rightList, rightLen);
            leftLen1 = (rightLen + 1) / 2;
            rightLen1 = rightLen - leftLen1;
            rightList = mergeSort(rightList, leftLen1, nextList, rightLen1);
        }

        // merge two list
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while (leftList != null && rightList != null) {
            if (leftList.val < rightList.val) {
                p.next = leftList;
                leftList = leftList.next;
            } else {
                p.next = rightList;
                rightList = rightList.next;
            }
            p = p.next;
        }
        if (leftList != null) {
            p.next = leftList;
        }
        if (rightList != null) {
            p.next = rightList;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        no148_sort_list obj = new no148_sort_list();
        ListNode head = new ListNode(3, new ListNode(4));
        ListNode res = obj.sortList(head);
        System.out.println(res);

        head = new ListNode(30, new ListNode(4));
        res = obj.sortList(head);
        System.out.println(res);

        head = new ListNode(3, new ListNode(4, new ListNode(1, new ListNode(2))));
        res = obj.sortList(head);
        System.out.println(res);
    }
}
