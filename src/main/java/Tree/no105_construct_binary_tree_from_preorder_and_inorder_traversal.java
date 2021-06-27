package Tree;

import common.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 */
public class no105_construct_binary_tree_from_preorder_and_inorder_traversal {
    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了49.34%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了80.25%的用户
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

    /** 这种方法 思路清晰一点
     * 执行用时：5 ms, 在所有 Java 提交中击败了27.90%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了61.70%的用户
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null) return null;

        int len1 = preorder.length;
        int len2 = inorder.length;
        if(len1<1 || len1!=len2) return null;

        return buildTree2(preorder,0,len1-1,inorder,0,len2-1);
    }
    public TreeNode buildTree2(int[] preorder,int start1,int end1, int[] inorder,int start2,int end2) {
        if(end1< start1 || end2 < start2) return null;

        int rootVal = preorder[start1];
        TreeNode root = new TreeNode(rootVal);

        int i = start2;
        while(inorder[i]!=rootVal) i++;
        int len = i - start2;

        root.left = buildTree2(preorder,start1+1,start1 + len,inorder,start2,i-1);
        root.right = buildTree2(preorder,start1 +len +1,end1,inorder,i+1,end2);

        return root;
    }
    public static void main(String args[]){
        no105_construct_binary_tree_from_preorder_and_inorder_traversal obj =
                new no105_construct_binary_tree_from_preorder_and_inorder_traversal();
        TreeNode root = obj.buildTree2(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(root);
    }
}
