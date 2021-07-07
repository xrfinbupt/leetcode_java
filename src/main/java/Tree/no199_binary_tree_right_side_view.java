package Tree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 199. 二叉树的右视图(Medium)
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 示例:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 */
public class no199_binary_tree_right_side_view {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了23.77%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了21.35%的用户
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode last = queue.peekLast();
            result.add(last.val);

            int size = queue.size();
            while(size-->0){
                TreeNode node = queue.removeFirst();
                if(node.left!=null)queue.addLast(node.left);
                if(node.right!=null)queue.addLast(node.right);
            }
        }
        return result;
    }
}
