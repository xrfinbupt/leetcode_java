package Tree;

import common.Node;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 116. 填充每个节点的下一个右侧节点指针(Medium)
 *
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 *  
 *
 * 提示：
 * 树中节点的数量少于 4096
 * -1000 <= node.val <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 */
public class no116_populating_next_right_pointers_in_each_node {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了45.70%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了93.92%的用户
     */
    public Node connect(Node root) {
        if(root == null) return null;
        Deque<Node> queue = new ArrayDeque<Node>();
        queue.add(root);
        while(queue.size()>0){
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node p = queue.removeFirst();
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
     * 参考官方解答
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了95.91%的用户
     */
    public Node connect2(Node root) {
        if(root == null) return null;
        Node leftMost = root;
        while(leftMost.left!=null){
            Node head = leftMost;
            while(head!=null){
                head.left.next = head.right;

                if(head.next!=null) head.right.next = head.next.left;
                head = head.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }
}
