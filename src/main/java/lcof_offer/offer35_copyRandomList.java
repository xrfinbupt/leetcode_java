package lcof_offer;

import common.multi.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xurongfei
 * @Date 2021/8/28
 * <p>
 * 剑指 Offer 35. 复杂链表的复制
 * <p>
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * <p>
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * <p>
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *  
 * 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * <p>
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 */

/**
 * class Node {
 *     int val;
 *     Node next;
 *     Node random;
 *
 *     public Node(int val) {
 *         this.val = val;
 *         this.next = null;
 *         this.random = null;
 *     }
 * }
 */
public class offer35_copyRandomList {
    private HashMap<Node,Node> nodeMap = new HashMap<>();

    private Node dummy = new Node(-1);

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了20.94%的用户
     */
    public Node copyRandomList(Node head) {
        if(head==null) return head;

        dummy.next = dfs(head);

        return dummy.next;
    }
    public Node dfs(Node head) {
        if(head==null) return null;
        if(nodeMap.containsKey(head)) return nodeMap.get(head);


        Node newNode = new Node(head.val);
        nodeMap.put(head,newNode);

        if(head.next!=null){
            newNode.next = dfs(head.next);
        }
        if(head.random!=null){
            newNode.random = dfs(head.random);
        }

        return newNode;
    }
}
