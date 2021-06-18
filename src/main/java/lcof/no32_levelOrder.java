package lcof;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 *
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 * [3,9,20,15,7]
 *  
 * 提示：
 * 节点总数 <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
 */
public class no32_levelOrder {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.74%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了90.92%的用户
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};

        List<Integer> printDatas = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode node = queue.removeFirst();
            printDatas.add(node.val);
            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);
        }
        int[] result = new int[printDatas.size()];
        int i = 0;
        for (Integer iter : printDatas) {
            result[i++] = iter;
        }

        return result;
    }
}
