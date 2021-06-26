package Tree;

import common.TreeNode;

/**
 * 114. 二叉树展开为链表
 *
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 *
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
public class no114_flatten_binary_tree_to_linked_list {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了67.90%的用户
     */
    public void flatten(TreeNode root) {
        dfs(root);
    }
    public TreeNode dfs(TreeNode root) {
        if(root==null) return null;

        TreeNode left = root.left;
        TreeNode right = root.right;

        TreeNode leftTail = null;
        if(left!=null) {
            root.left = null;
            root.right = left;
            leftTail = dfs(left);
            if (leftTail != null) leftTail.right = right;
        }

        if(right!=null) {
            TreeNode rightTail = dfs(right);
            if (rightTail != null) return rightTail;
        }else if(leftTail!=null){
            return leftTail;
        }
        return root;
    }

    public static void main(String args[]){
        TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(2);
        //root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);

        root.right = new TreeNode(5);
        //root.right.left = new TreeNode(31);
        root.right.right = new TreeNode(6);

        no114_flatten_binary_tree_to_linked_list obj = new no114_flatten_binary_tree_to_linked_list();
        obj.flatten(root);
        System.out.println();
    }
}
