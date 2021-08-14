package lcof_offer;

import common.TreeNode;

import javax.transaction.TransactionRequiredException;

/**
 * 剑指 Offer 28. 对称的二叉树
 *
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 * 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
 *
 * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
 */
public class offer28_isSymmetric {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了17.02%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了6.38%的用户
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        TreeNode mirrorTreeRoot = recursive_getTheMirrorTree(root);
        return recursive_isSymmetric(root,mirrorTreeRoot);
    }

    public boolean recursive_isSymmetric(TreeNode root,TreeNode mirrorTreeRoot) {
        if(root==null&&mirrorTreeRoot==null) return true;
        else if(root == null || mirrorTreeRoot == null) return false;
        else{
            if(root.val!=mirrorTreeRoot.val) return false;
            else{
                boolean left = recursive_isSymmetric(root.left,mirrorTreeRoot.left);
                boolean right = recursive_isSymmetric(root.right,mirrorTreeRoot.right);
                if(right && left) return true;
                else return false;
            }
        }
    }
    public TreeNode recursive_getTheMirrorTree(TreeNode root) {
        if(root == null) return null;
        TreeNode newRoot = new TreeNode(root.val);
        TreeNode left = root.left;
        TreeNode right = root.right;

        if(left!=null) left = recursive_getTheMirrorTree(left);
        if(right!=null) right = recursive_getTheMirrorTree(right);
        newRoot.left = right;
        newRoot.right = left;
        return newRoot;
    }
}
