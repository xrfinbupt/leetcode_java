package lcof;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 *
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *  
 * 提示：
 * 节点总数 <= 1000
 * 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
 */
public class no32_levelOrder_II {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了95.03%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了81.86%的用户
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        ArrayDeque<TreeNode> firstQueue = new ArrayDeque<TreeNode>();
        firstQueue.add(root);
        ArrayDeque<TreeNode> secondQueue = new ArrayDeque<TreeNode>();
        List<Integer> datas = new ArrayList<>();
        while (firstQueue.size() > 0) {
            TreeNode node = firstQueue.removeFirst();
            datas.add(node.val);
            if (node.left != null) secondQueue.addLast(node.left);
            if (node.right != null) secondQueue.addLast(node.right);

            if (firstQueue.size() == 0) {
                ArrayDeque<TreeNode> temp = firstQueue;
                firstQueue = secondQueue;
                secondQueue = temp;

                result.add(datas);
                datas = new ArrayList<>();
            }
        }
        return result;
    }
}
