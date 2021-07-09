package Tree;

import common.TreeNode;

/**
 * 222. 完全二叉树的节点个数(Medium)
 *
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2^h 个节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 *
 * 示例 2：
 * 输入：root = []
 * 输出：0
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 *  
 * 提示：
 * 树中节点的数目范围是[0, 5 * 104]
 * 0 <= Node.val <= 5 * 104
 * 题目数据保证输入的树是 完全二叉树
 *
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 *
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 */
public class no222_count_complete_tree_nodes {
    int result = 0;
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了56.13%的用户
     */
    public int countNodes(TreeNode root) {
        dfs(root);
        return result;
    }
    private void dfs(TreeNode root){
        if(root == null) return;
        result++;
        dfs(root.left);
        dfs(root.right);
    }

    int maxLevel = 0;
    int lackCount = 0;
    boolean skipDfs = false;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了94.45%的用户
     */
    public int countNodes2(TreeNode root) {
        if(root == null) return 0;
        maxLevel = -1;
        for(TreeNode p = root;p!=null;p = p.left){
            maxLevel++;
        }
        dfs2(root,0);

        return (1<<(maxLevel+1)) -1 - lackCount;
    }
    private void dfs2(TreeNode root,int level){
        if(root == null || skipDfs) return;

        if(maxLevel == level + 1){
            if (root.right == null) lackCount++;
            if (root.left == null) lackCount++;

            if(root.left !=null) skipDfs = true;
            return;
        }
        dfs2(root.right,level+1);
        dfs2(root.left,level+1);
    }
    public static void main(String args[]){
        TreeNode root  = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        no222_count_complete_tree_nodes obj = new no222_count_complete_tree_nodes();
        int result = obj.countNodes2(root);
        System.out.println(result);

        obj.maxLevel = 0;
        obj.lackCount = 0;
        obj.skipDfs = false;
        root.right.left = new TreeNode(6);
        result = obj.countNodes2(root);
        System.out.println(result);
    }
}
