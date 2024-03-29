package lcof_offer;

import common.TreeNode;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * 注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
 */
public class offer68_lowestCommonAncestor {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了100%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了48.30%的用户
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        TreeNode result = recursive(root,min,max);
        return result;
    }
    private TreeNode recursive(TreeNode root,int min,int max){
        if(root == null) return null;
        if(root.val == min || root.val == max) return root;
        else if(root.val < min){
            return recursive(root.right,min,max);
        }else if(root.val > max){
            return recursive(root.left,min,max);
        }else{
            return root;
        }
    }
}
