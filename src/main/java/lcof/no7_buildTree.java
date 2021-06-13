package lcof;

import common.TreeNode;

/**
 * 剑指 Offer 07. 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,8,15,20,7,6]
 * 中序遍历 inorder  = [8,9,15,3,7,20,6]
 * 返回如下的二叉树：
 *      3
 *    /   \
 *   9     20
 *  / \   /  \
 * 8  15 7    6
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 * 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 */
public class no7_buildTree {

    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了32.12%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了91.91%的用户
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        int len2 = inorder.length;
        if(len<1 || len!=len2) return null;

        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        int i = 0;
        while(inorder[i]!=rootVal) i++;
        sub_buildTree(root,preorder,i==0?-1:1,i==0?-1:i,inorder,i==0?-1:0,i==0?-1:i-1,true);
        sub_buildTree(root,preorder,i==len-1?-1:i+1,i==len-1?-1:len-1,inorder,i==len-1?-1:i+1,i==len-1?-1:len-1,false);

        return root;
    }

    private void sub_buildTree(TreeNode root,int[] preorder,int preI,int preJ, int[] inorder,int inI,int inJ,boolean leftFlag) {
        if(preI>=0 && preJ >=0 && inI>=0 && inJ >=0){
            int len = preJ - preI + 1;
            int val = preorder[preI];
            TreeNode node = new TreeNode(val);

            int i = inI;
            while(inorder[i]!=val && i <= inJ) i++;
            int leftLen = i - inI + 1;
            if(leftFlag) {
                root.left = node;
            } else {
                root.right = node;
            }
            if(len>1) {
                sub_buildTree(node, preorder, i == inI ? -1 : preI + 1, i == inI ? -1 : preI + leftLen -1, inorder, i == inI ? -1 : inI, i == inI ? -1 : i - 1, true);
                sub_buildTree(node, preorder, i == inJ ? -1 : preI + leftLen, i == inJ ? -1 : preJ, inorder, i == inJ ? -1 : i+1, i == inJ ? -1 : inJ, false);
            }
        }

        return;
    }
    public static void main(String args[]){
        no7_buildTree obj = new no7_buildTree();
        TreeNode result = obj.buildTree(new int[]{3,9,8,15,20,7,6},new int[]{8,9,15,3,7,20,6});
        System.out.println(result);

        result = obj.buildTree(new int[]{1,2,3},new int[]{2,3,1});
        System.out.println(result);
    }
}
