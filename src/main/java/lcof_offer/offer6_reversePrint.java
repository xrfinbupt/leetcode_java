package lcof_offer;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class offer6_reversePrint {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了38.16%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了90.13%的用户
     */
    public int[] reversePrint(ListNode head) {
        Deque<Integer> result = new ArrayDeque<>();
        while(head!=null){
            result.addFirst(head.val);
            head = head.next;
        }
        int array[] = new int[result.size()];
        int i = 0;
        while(result.size()>0) array[i++] = result.removeFirst();

        return array;
    }
}
