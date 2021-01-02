package high_frequency.easy;

// https://github.com/azl397985856/leetcode/blob/master/problems/binode-lcci.md
public class no1_binode_lcci {
    TreeNode first = null;

    public static void main(String args[]){
        Integer []array = new Integer[]{8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
        TreeNode root = TreeNode.getTreeNode(array);
        System.out.println(root);

        no1_binode_lcci app = new no1_binode_lcci();
        TreeNode result = app.convertBiNode(root);
        System.out.println(result);
    }

    //
    // [8,4,12,2,6,10,14,1,3,5,7,9,11,13,15]
    //
    public TreeNode convertBiNode(TreeNode root) {
        if(root == null) return null;

        TreeNode tail = null;
        if(root.left == null) first = root;
        else{
            tail = DFS(root.left,null);
            tail.right = root;
            root.left = null;
        }

        if(root.right!=null) {
            DFS(root.right,root);
        }

        return first;
    }

    // 重点是记录pre记录
    // https://www.suxieban.com/index.html# 画二叉树图 好分析
    public TreeNode DFS(TreeNode root,TreeNode pre) {
        if(first == null && root!=null && root.left==null){
            first = root;
        }
        TreeNode tail1 = null;
        if(root.left!=null){
            tail1 = DFS(root.left,pre);

            tail1.right = root;
            root.left = null;
        } else if(root.left==null && pre!=null){
            pre.right = root;
        }

        TreeNode tail2 = root;
        if(root.right!=null){
            tail2 = DFS(root.right,root);
        }

        return tail2;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */