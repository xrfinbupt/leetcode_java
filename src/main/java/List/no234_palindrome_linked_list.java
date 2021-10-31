package List;

import common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * <p>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *  
 * 提示：
 * 链表中节点数目在范围[1, 10^5] 内
 * 0 <= Node.val <= 9
 *  
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 */
public class no234_palindrome_linked_list {
    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了40.32%的用户
     * 内存消耗：51 MB, 在所有 Java 提交中击败了24.85%的用户
     */
    public boolean isPalindrome2(ListNode head) {
        List<Integer> valList = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            valList.add(p.val);
            p = p.next;
        }
        int len = valList.size();
        int i = 0, j = len - 1;
        while (i <= j) {
            int ii = valList.get(i);
            int jj = valList.get(j);
            if (ii == jj) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了86.63%的用户
     * 内存消耗：47.8 MB, 在所有 Java 提交中击败了98.61%的用户
     */
    public boolean isPalindrome(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            p = p.next;
            len++;
        }
        if(len==1){
            return true;
        }

        int mid = (1 + len) / 2;
        p = head;
        ListNode pre = head;
        while (mid-- > 0) {
            pre = p;
            p = p.next;
        }

        ListNode p2 = p;
        while (p2 != null) {
            ListNode temp = p2.next;
            p2.next = pre;
            pre = p2;
            p2 = temp;
        }

        p2 = pre;
        p = head;
        mid = (1 + len) / 2;
        while (mid-- > 0) {
            if (p2.val == p.val) {
                p2 = p2.next;
                p = p.next;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[]args){
        ListNode head = new ListNode(1,new ListNode(2));
        no234_palindrome_linked_list obj = new no234_palindrome_linked_list();
        System.out.println(obj.isPalindrome(head));

        head = new ListNode(1,new ListNode(2,new ListNode(2,new ListNode(1))));
        System.out.println(obj.isPalindrome(head));

        head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(2,new ListNode(1)))));
        System.out.println(obj.isPalindrome(head));
    }
}
