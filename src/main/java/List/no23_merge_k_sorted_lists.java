package List;

import common.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class no23_merge_k_sorted_lists {
    PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    });

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        ListNode curr = null;
        if(lists.length == 0) return result;
        if(lists.length == 1) return lists[0];

        queue.clear();
        int count = lists.length;
        int emptyItem = 0;

        while(true){
            emptyItem = 0;
            for(int i=0;i<count;i++){
                ListNode temp =  lists[i];
                if(temp==null) emptyItem++;
                else{
                    if(queue.size()==0 || queue.peek().val > temp.val){
                        lists[i] = temp.next;
                        queue.offer(temp);
                    }
                }
            }
            if(emptyItem == count && queue.size()==0){
                break;
            }

            if(result==null){
                result = queue.remove();
                curr = result;
                result.next = null;
            }else{
                curr.next = queue.remove();
                curr = curr.next;
                curr.next = null;
            }
        }

        while(queue.size()>0){
            curr.next = queue.remove();
            curr = curr.next;
            curr.next = null;
        }
        return result;
    }
    public static void main(String args[]) {
        no23_merge_k_sorted_lists obj = new no23_merge_k_sorted_lists();
        ListNode[] array = new ListNode[2];
        ListNode root = new ListNode(1);
        ListNode p = root;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        p = p.next;
        p.next = new ListNode(5);
        p = p.next;
        array[0]=root;

        root = new ListNode(2);
        p = root;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        p = p.next;
        p.next = new ListNode(5);
        p = p.next;
        array[1]=root;

        obj.mergeKLists(array);
    }
}
