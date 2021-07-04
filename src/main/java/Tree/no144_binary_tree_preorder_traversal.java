package Tree;

import com.sun.jmx.remote.internal.ArrayQueue;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *  
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 */
public class no144_binary_tree_preorder_traversal {
    private List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        dfs(root);
        return result;
    }
    public void dfs(TreeNode root){
        if(root==null)return;
        result.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    /**
     * 非迭代
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了47.63%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了48.59%的用户
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(queue.size()>0){
            TreeNode node = queue.removeFirst();
            ans.add(node.val);
            if (node.right != null) queue.addFirst(node.right);
            if (node.left != null) queue.addFirst(node.left);
        }

        return ans;
    }
}
