package Tree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 173. 二叉搜索树迭代器(Medium)
 *
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 *
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 *
 * 示例：
 * 输入
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * 解释
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 *  
 * 提示：
 * 树中节点的数目在范围 [1, 105] 内
 * 0 <= Node.val <= 106
 * 最多调用 105 次 hasNext 和 next 操作
 *  
 * 进阶：
 * 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高度。
 *
 * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator
 */
public class no173_binary_search_tree_iterator {
    private LinkedList<Integer> result = new LinkedList<>();

    private ArrayDeque<TreeNode> result1 = new ArrayDeque<>();

    /**
     * 执行用时：24 ms, 在所有 Java 提交中击败了30.72%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了47.39%的用户
     */
    public no173_binary_search_tree_iterator(TreeNode root) {
        result.clear();
        dfs(root);
    }

    /**
     * 执行用时：18 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了61.36%的用户
     */
    public void BSTIterator(TreeNode root) {
        result1.clear();
        queueLeft(root);
    }
    private void queueLeft(TreeNode root){
        if(root == null) return;
        TreeNode p = root;
        while(p!=null){
            result1.addFirst(p);
            p = p.left;
        }
    }
    private void dfs(TreeNode root){
        if(root == null)return;

        if (root.left != null) dfs(root.left);
        result.add(root.val);
        if (root.right != null) dfs(root.right);
    }

    public int next() {
        int val = result.removeFirst();
        return val;
    }
    public int next1() {
        TreeNode node = result1.removeFirst();
        if(node.right!=null){
            queueLeft(node.right);
        }
        return node.val;
    }

    public boolean hasNext() {
        if(result.size()>0)return true;
        return false;
    }
    public boolean hasNext1() {
        if(result1.size()>0)return true;
        return false;
    }
    public static void main(String args[]){
        no173_binary_search_tree_iterator obj = new no173_binary_search_tree_iterator(null);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        obj.BSTIterator(root);

        while(obj.hasNext1()){
            System.out.println(obj.next1());
        }
    }
}
