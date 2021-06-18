package lcof;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 *
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
 *   [20,9],
 *   [15,7]
 * ]
 *  
 * 提示：
 * 节点总数 <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
 */
public class no32_levelOrder_III {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.92%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了87.76%的用户
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> datas = new ArrayList<>();
        boolean rightDir = true;

        ArrayDeque<TreeNode> first = new ArrayDeque<TreeNode>();
        first.add(root);
        ArrayDeque<TreeNode> second = new ArrayDeque<TreeNode>();
        while (first.size() > 0) {
            TreeNode node = null;
            if (rightDir) node = first.removeFirst();
            else {
                node = first.removeLast();
            }
            datas.add(node.val);

            if (rightDir) {
                if (node.left != null) second.addLast(node.left);
                if (node.right != null) second.addLast(node.right);
            } else {
                if (node.right != null) second.addFirst(node.right);
                if (node.left != null) second.addFirst(node.left);
            }

            if (first.size() == 0) {
                rightDir = !rightDir;
                result.add(datas);
                datas = new ArrayList<>();
                ArrayDeque<TreeNode> temp = first;
                first = second;
                second = temp;
            }
        }

        return result;
    }
}
