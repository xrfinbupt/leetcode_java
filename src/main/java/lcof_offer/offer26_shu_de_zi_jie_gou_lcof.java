package lcof_offer;

import common.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class offer26_shu_de_zi_jie_gou_lcof {
    Boolean result = null;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了66.24%的用户
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null || B==null) return false;

        dfs(A,B);
        if(result!=null) return result;
        return false;
    }
    private void dfs(TreeNode A,TreeNode B){
        if(result!=null || A==null) return;

        if(compareTree(A,B)){
            result = true;
            return;
        }else {
            if(A.left!=null) dfs(A.left, B);
            if(A.right!=null) dfs(A.right, B);
        }
    }
    private boolean compareTree(TreeNode A,TreeNode B){
        if(B==null) return true;
        if(A==null) return false;

        if(A.val != B.val){
            return false;
        }else{
            boolean left = compareTree(A.left,B.left);
            boolean right = compareTree(A.right,B.right);
            return left && right;
        }
    }
    public static void main(String[] args){
        offer26_shu_de_zi_jie_gou_lcof obj = new offer26_shu_de_zi_jie_gou_lcof();
        TreeNode A = new TreeNode(1,new TreeNode(2,new TreeNode(4),null),new TreeNode(3));
        TreeNode B = new TreeNode(3,new TreeNode(4),null);
        boolean res = obj.isSubStructure(A,B);
        System.out.println(res);

        B = new TreeNode(2,new TreeNode(4),null);
        res = obj.isSubStructure(A,B);
        System.out.println(res);
    }
}