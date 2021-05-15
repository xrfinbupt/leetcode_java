package Tree;

import com.sun.jmx.remote.internal.ArrayQueue;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历 (中等)
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
public class no102_binary_tree_level_order_traversal {
    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了10.33%的用户
     * 有点慢啊
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return result;
        ArrayDeque<TreeNode> queue1 = new ArrayDeque<TreeNode>();
        queue1.add(root);
        ArrayDeque<TreeNode> queue2 = new ArrayDeque<TreeNode>();

        while (true) {
            List<Integer> tempResult = new ArrayList<>();
            while (queue1.size() > 0) {
                TreeNode node = queue1.remove();
                tempResult.add(node.val);

                if (node.left != null) queue2.add(node.left);
                if (node.right != null) queue2.add(node.right);
            }
            result.add(tempResult);

            ArrayDeque<TreeNode> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            if (queue1.size() == 0) break;
        }

        return result;
    }

    /**
     * 优化一下存储
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return result;
        ArrayDeque<TreeNode> queue1 = new ArrayDeque<TreeNode>();
        queue1.add(root);

        while (queue1.size() > 0) {
            int size = queue1.size();
            List<Integer> tempResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue1.remove();
                tempResult.add(node.val);

                if (node.left != null) queue1.add(node.left);
                if (node.right != null) queue1.add(node.right);
            }
            result.add(tempResult);
            if (queue1.size() == 0) break;
        }

        return result;
    }

    /**
     * 参考官网解答 利用array的特性
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) return result;

        sub_levelOrder3(root,0);

        return result;
    }

    public void sub_levelOrder3(TreeNode root,int level) {
        if (root == null) return;
        if(result.size() == level){
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        sub_levelOrder3(root.left,level+1);
        sub_levelOrder3(root.right,level+1);

        return ;
    }
}
