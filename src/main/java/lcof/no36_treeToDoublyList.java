package lcof;

import common.Node;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * <p>
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * <p>
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 * <p>
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * <p>
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *  
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 * <p>
 * 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * 注意：此题对比原题有改动。
 * <p>
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 */
public class no36_treeToDoublyList {
    private Node first = null;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了47.79%的用户
     */
    public Node treeToDoublyList(Node root) {
        if(root == null) return root;

        Node last = recursive(root, null);

        first.left = last;
        last.right = first;

        return first;
    }

    private Node recursive(Node root, Node pre) {
        if(root == null) return pre;
        if (root.left == null) {
            if (first == null) {
                first = root;
            }
        }

        Node preNode = recursive(root.left, pre);
        if (preNode != null) {
            preNode.right = root;
            root.left = preNode;
        }

        return recursive(root.right, root);
    }
}
