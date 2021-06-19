package Tree;

import common.TreeNode;

/**
 * 437. 路径总和 III
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 */
public class no437_path_sum_iii {
    int result = 0;

    /**
     * 执行用时：22 ms, 在所有 Java 提交中击败了57.47%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了24.80%的用户
     */
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null) return result;
        recursive(root,targetSum);
        return result;
    }
    public void recursive(TreeNode root, int targetSum) {
        if(root==null) return ;
        recursive_pathSum(root,targetSum,0);

        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left!=null){
            recursive(left,targetSum);
        }
        if(right!=null){
            recursive(right,targetSum);
        }

        return ;
    }

    public void recursive_pathSum(TreeNode root, int targetSum,int sum) {
        if(root==null) return ;
        int val = root.val;
        sum += val;
        if(sum==targetSum){
            result++;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left!=null){
            recursive_pathSum(left,targetSum,sum);
        }
        if(right!=null){
            recursive_pathSum(right,targetSum,sum);
        }

        return ;
    }

    public static void main(String args[]){
        no437_path_sum_iii obj = new no437_path_sum_iii();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3 );
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);

        int result = obj.pathSum(root,3);
        System.out.println(result);
    }
}
