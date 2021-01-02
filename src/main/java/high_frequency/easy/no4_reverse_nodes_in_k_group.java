package high_frequency.easy;

// https://github.com/azl397985856/leetcode/blob/master/problems/25.reverse-nodes-in-k-groups.md
public class no4_reverse_nodes_in_k_group {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k<=1) return head;

        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode curr = head;
        ListNode start = head;
        ListNode pre = dump;

        int index = 0;
        int count = 0;
        while(curr!=null){
            if(index!=0 && index%k == 0){
                ListNode pre1 = start;
                ListNode curr1 = start.next;
                while(curr1!=curr){
                    ListNode temp = curr1.next;
                    curr1.next = pre1;

                    pre1 = curr1;
                    curr1 = temp;
                }
                pre.next = pre1;
                start.next = curr;

                pre = start;
                start = curr;

                curr = curr.next;
                count = 1;
            }else{
                curr = curr.next;
                count++;
            }
            index++;
        }

        if(count==k){
            ListNode pre1 = start;
            ListNode curr1 = start.next;
            while(curr1!=null){
                ListNode temp = curr1.next;
                curr1.next = pre1;

                pre1 = curr1;
                curr1 = temp;
            }
            pre.next = pre1;
            start.next = null;
        }
        return dump.next;
    }

    public static void main(String args[]){
        no4_reverse_nodes_in_k_group obj = new no4_reverse_nodes_in_k_group();
        ListNode root = new ListNode(1);
        ListNode p = root;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        p = p.next;
        //p.next = new ListNode(5);
        //p = p.next;

        obj.reverseKGroup(root,2);
    }
}
