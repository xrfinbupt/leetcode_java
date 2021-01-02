package high_frequency.easy;

// https://leetcode-cn.com/problems/reverse-linked-list-ii/
// https://github.com/azl397985856/leetcode/blob/master/problems/92.reverse-linked-list-ii.md
public class no3_reverse_linked_list_ii {

    // m = 1             n < len or n = len
    // m > 1 and m < n   n < len or n = len
    // m > 1 and m = n   n < len or n = len
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || head.next == null || m == n) return head;

        ListNode p1 , p2 , p3,p4;
        p1 = p2 = p3 = p4 = null;

        ListNode dump = new ListNode(0);
        dump.next = head;

        int len = 0;
        ListNode iter = head;
        while(iter!=null){
            len++;
            if(len==m-1){
                p1 = iter;
            }
            if(len == m){
                p2 = iter;
            }
            if(len == n){
                p3 = iter;
            }
            if(len == n+1){
                p4 = iter;
                break;
            }

            iter = iter.next;
        }
        if(p1 == null){
            p1 = dump;
        }

        // [p2,p3]
        ListNode pre = p2;
        ListNode curr = p2.next;
        while(curr!=p4){
            ListNode temp = curr.next;
            curr.next = pre;

            pre = curr;
            curr = temp;
        }
        p1.next = p3;
        p2.next = p4;

        return dump.next;
    }

    public static void main(String args[]){
        no3_reverse_linked_list_ii obj = new no3_reverse_linked_list_ii();
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

        obj.reverseBetween(root,2,4);
    }
}
