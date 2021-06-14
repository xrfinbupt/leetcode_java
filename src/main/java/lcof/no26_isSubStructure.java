package lcof;

import common.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *
 * 给定的树 B：
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 * 限制：
 * 0 <= 节点个数 <= 10000
 *
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 */
public class no26_isSubStructure {
    boolean result =false;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了92.41%的用户
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null) return result;

        result = false;
        sub_isSubStructure(A,B);

        return result;
    }

    public void sub_isSubStructure(TreeNode A, TreeNode B) {
        if(result) return;

        if(A.val == B.val){
            if(isSubStructure2(A,B)){
                result = true;
                return;
            }
        }
        if(A.left!=null) sub_isSubStructure(A.left,B);
        if(A.right!=null) sub_isSubStructure(A.right,B);

        return;
    }

    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if(A==null) return false;
        if(A.val != B.val){
            return false;
        }
        if(B.left!=null) {
            if(!isSubStructure2(A.left, B.left)) return false;
        }
        if(B.right!=null){
            if(!isSubStructure2(A.right, B.right)) return false;
        }
        return true;
    }
}
