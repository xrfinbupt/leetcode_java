package List;

import common.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 *
 */
public class no19_remove_nth_node_from_end_of_list {
    /**
     * 双指针 一个fast 一个slow
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pFast = null,pSlow = null;
        for(int i=1;i<=n;i++){
            if(pFast == null){
                pFast = head;
            }else{
                pFast = pFast.next;
            }
        }
        if(pFast.next==null){
            return head.next;
        }

        while(pFast.next!=null){
            pFast = pFast.next;
            if(pSlow == null){
                pSlow = head;
            }else{
                pSlow = pSlow.next;
            }
        }

        if(pSlow==null) return null;

        ListNode delete = pSlow.next;
        pSlow.next = delete.next;
        delete.next = null;

        return head;
    }
}
