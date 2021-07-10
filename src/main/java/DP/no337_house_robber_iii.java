package DP;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 */
public class no337_house_robber_iii {
    Map<TreeNode,Integer> f  = new HashMap<>();
    Map<TreeNode,Integer> g  = new HashMap<>();

    /**
     * 参考官方解答
     * 执行用时：3 ms, 在所有 Java 提交中击败了44.56%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了56.99%的用户
     */
    public int rob(TreeNode root) {
        dfs(root);

        return Math.max(f.getOrDefault(root,0),g.getOrDefault(root,0));
    }
    public void dfs(TreeNode root){
        if(root == null) return ;

        dfs(root.left);
        dfs(root.right);

        f.put(root,root.val+ g.getOrDefault(root.left,0)+g.getOrDefault(root.right,0));
        g.put(root,Math.max(f.getOrDefault(root.left,0),g.getOrDefault(root.left,0))+
                Math.max(f.getOrDefault(root.right,0),g.getOrDefault(root.right,0)));
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了48.22%的用户
     */
    public int rob2(TreeNode root) {
        int[] val =  dfs2(root);

        return Math.max(val[0],val[1]);
    }

    public int[] dfs2(TreeNode root){
        if(root == null) return null;

        int[] left = dfs2(root.left);
        int[] right = dfs2(root.right);

        int leftVal0 = left==null?0:left[0];
        int leftVal1 = left==null?0:left[1];
        int rightVal0 = right==null?0:right[0];
        int rightVal1 = right==null?0:right[1];

        int ff = root.val + leftVal1 + rightVal1;
        int gg = Math.max(leftVal0,leftVal1) + Math.max(rightVal0,rightVal1);

        return new int[]{ff,gg};
    }

    public static void main(String args[]){
        no337_house_robber_iii obj = new no337_house_robber_iii();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        int result = obj.rob2(root);
        System.out.println(result);
    }
}
