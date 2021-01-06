package high_frequency.easy;

import common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 树 43.156.上下翻转二叉树(Medium)
 * <p>
 * 给定一个二叉树，其中所有右节点要么是具有兄弟节点的叶节点(有一个共享相同父节点的左节点)或空白，
 * 将其倒置并将其转换为树，其中原来的右节点变为左叶子节点。返回新的根节点。
 *
 * 样例1
 * 输入: {1,2,3,4,5}
 * 输出: {4,5,2,#,#,3,1}
 * 说明:
 * 输入是
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * 输出是
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 *
 * 样例2
 * 输入: {1,2,3,4}
 * 输出: {4,#,2,3,1}
 * 说明:
 * 输入是
 *     1
 *    / \
 *   2   3
 *  /
 * 4
 * 输出是
 *    4
 *     \
 *      2
 *     / \
 *    3   1
 *
 * https://www.lintcode.com/problem/binary-tree-upside-down/description
 */
public class no14_binary_tree_upside_down {

    /**
     * 方法1 迭代
     *
     * @param root: the root of binary tree
     * @return: new root
     */
    public TreeNode upsideDownBinaryTree_method1(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;

        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode iter = root;
        while (iter != null) {
            queue.add(iter);
            if (iter.right != null) {
                map.put(iter.left, iter.right);
            }
            TreeNode temp = iter.left;
            iter.left = null;
            iter.right = null;
            iter = temp;
        }
        TreeNode head = null;
        iter = null;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.pollLast();
            if (head == null) {
                head = temp;
                iter = head;
                TreeNode tempLeft = map.get(head);
                if (tempLeft != null)
                    head.left = tempLeft;
            } else {
                iter.right = temp;
                iter = temp;
                TreeNode tempLeft = map.get(temp);
                if (tempLeft != null)
                    temp.left = tempLeft;
            }
        }
        return head;
    }

    /**
     * 方法2 递归
     *
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree_method2(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;

        TreeNode node = upsideDownBinaryTree_method2(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return node;
    }
}
