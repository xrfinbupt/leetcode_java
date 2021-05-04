package List;

import common.ListNode;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 *
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
public class no24_swap_nodes_in_pairs {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next == null){
            return head;
        }

        ListNode pre = null;
        ListNode p1 = head, p2 = p1.next;
        ListNode last = p2.next;

        while(true) {
            p2.next = p1;
            p1.next = last;

            if(pre==null){
                head = p2;
            }else{
                pre.next = p2;
            }

            pre = p1;
            if(pre.next == null || pre.next.next == null){
                break;
            }
            p1 = pre.next;
            p2 = p1.next;
            last = p2.next;
        }

        return head;
    }
}
