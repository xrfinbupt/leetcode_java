package Tree;

import common.TreeNode;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 */
public class no106_construct_binary_tree_from_inorder_and_postorder_traversal {
    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了48.94%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了49.58%的用户
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null) return null;

        int inLen = inorder.length;
        int postLen = postorder.length;
        if(inLen!=postLen) return null;

        TreeNode root = buildTree(inorder,0,inLen-1,postorder,0,postLen-1);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int start1,int end1,int[] postorder,int start2,int end2) {
        if(end1<start1 || end2 < start2) return null;

        int rootVal = postorder[end2];
        int i=start1;
        for(;i<=end1;i++){
            if(inorder[i]==rootVal) break;
        }
        int len = i-start1;

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorder,start1,i-1,postorder,start2,start2+(len-1));
        root.right = buildTree(inorder,i+1,end1,postorder,start2+(len),end2-1);

        return root;
    }
}
