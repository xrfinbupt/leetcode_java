package Tree;

import common.Node1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II (Medium)
 *
 * 给定一个二叉树
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *  
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *  
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 * 提示：
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 *
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 */
public class no117_populating_next_right_pointers_in_each_node_ii {
    /**
     * bfs
     */
    public Node1 connect(Node1 root) {
        if (root == null) return null;
        Deque<Node1> queue = new ArrayDeque<Node1>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            Node1 pre = null;
            for (int i = 0; i < size; i++) {
                Node1 p = queue.removeFirst();
                if (p.left != null) queue.addLast(p.left);
                if (p.right != null) queue.addLast(p.right);

                if (pre == null) {
                    pre = p;
                } else {
                    pre.next = p;
                    pre = p;
                }
            }
        }
        return root;
    }

    /**
     * bfs
     * 执行用时：1 ms, 在所有 Java 提交中击败了65.59%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了47.55%的用户
     */
    public Node1 connect2(Node1 root) {
        if (root == null) return null;
        Node1 leftMost = root;
        while(leftMost!=null){
            Node1 head = leftMost;
            Node1 left = null;
            boolean nextLeftMostFlag = false;
            while(head!=null){
                // left
                while(head!=null && left==null){
                    left = head.left;
                    if(left==null) left = head.right;

                    if(left==null) head = head.next;
                }
                if (left == null) break;
                if (!nextLeftMostFlag) {
                    nextLeftMostFlag = true;
                    leftMost = left;
                }

                Node1 right = null;
                boolean flag = false;
                while (head != null) {
                    if (!flag) {
                        if (head.left == left) {
                            flag = true;
                            right = head.right;
                        } else if (head.right == left) {
                            flag = true;
                        }
                    } else {
                        right = head.left;
                        if (right == null) right = head.right;
                    }

                    if (right != null) break;
                    head = head.next;
                }

                if (right != null) {
                    left.next = right;
                    left = right;
                }
            }
            if (!nextLeftMostFlag) {
                break;
            }
        }
        return root;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了65.59%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了74.81%的用户
     */
    public Node1 connect3(Node1 root) {
        if (root == null) return null;
        Node1 nextNode = root;
        while (nextNode!=null && nextNode.left != null||nextNode!=null && nextNode.right !=null) {
            Node1 head = nextNode;
            nextNode = null;
            Node1 left = null;
            Node1 right = null;
            while (head != null) {
                if (left == null) {
                    left = head.left;
                    if (left == null) left = head.right;
                    else right = head.right;
                } else {
                    right = head.left;
                    if (right == null) right = head.right;
                    else{
                        left.next = right;
                        left = right;
                        right = head.right;
                    }
                }

                if (nextNode==null && head.left != null && (head.left.left != null || head.left.right != null)) {
                    nextNode = head.left;
                }
                if (nextNode==null && head.right != null && (head.right.left != null || head.right.right != null)) {
                    nextNode = head.right;
                }

                if (right != null) {
                    left.next = right;
                    left = right;
                    right = null;
                }
                head = head.next;
            }
        }
        return root;
    }

    /**
     * 参考官方解答
     * bfs
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了66.89%的用户
     */
    Node1 last = null,nextStart = null;
    public Node1 connect4(Node1 root) {
        if (root == null) return null;
        Node1 start = root;
        while (start!=null) {
            last = null;
            nextStart = null;
            for(Node1 p = start;p!=null;p=p.next){
                if(p.left!=null) handle(p.left);
                if(p.right!=null) handle(p.right);
            }
            start = nextStart;
        }
        return root;
    }
    public void handle(Node1 p){
        if(last!=null){
            last.next = p;
        }
        if(nextStart==null){
            nextStart = p;
        }
        last = p;
    }

    public static void main(String args[]){
        no117_populating_next_right_pointers_in_each_node_ii obj = new no117_populating_next_right_pointers_in_each_node_ii();
        Node1 root = new Node1(1);
        root.left = new Node1(2);
        root.right = new Node1(3);
        root.left.left = new Node1(4);
        root.left.right = new Node1(5);

        root.right.right = new Node1(7);

        obj.connect4(root);
        System.out.println();


        root = new Node1(3);
        root.left = new Node1(9);
        root.right = new Node1(20);
        root.right.left = new Node1(15);
        root.right.right = new Node1(7);


        obj.connect4(root);
        System.out.println();
    }
}
