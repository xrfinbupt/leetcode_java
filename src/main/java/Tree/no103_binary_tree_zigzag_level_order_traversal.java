package Tree;

import common.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 */
public class no103_binary_tree_zigzag_level_order_traversal {
    private List<List<Integer>> result = new ArrayList<>();
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了19.90%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了70.53%的用户
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        ArrayDeque<TreeNode> second = new ArrayDeque<>();
        queue.add(root);
        boolean dirRight = true;
        List<Integer> data = new ArrayList<>();
        while (queue.size() > 0) {
            TreeNode node = null;
            if (dirRight) {
                node = queue.removeFirst();
                if (node.left != null) second.addLast(node.left);
                if (node.right != null) second.addLast(node.right);
            } else {
                node = queue.removeLast();
                if (node.right != null) second.addFirst(node.right);
                if (node.left != null) second.addFirst(node.left);
            }
            data.add(node.val);
            if (queue.size() == 0) {
                dirRight = !dirRight;
                result.add(data);
                data = new ArrayList<>();
                ArrayDeque<TreeNode> temp = queue;
                queue = second;
                second = temp;
            }
        }

        return result;
    }

    /**
     * 参考官方解答
     * 执行用时：2 ms, 在所有 Java 提交中击败了19.90%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了69.09%的用户
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean dirRight = true;
        queue.add(root);
        int size = queue.size();
        while(size>0){
            LinkedList<Integer> data = new LinkedList<>();
            for(int i=1;i<=size;i++){
                TreeNode node = queue.poll();
                if(dirRight){
                    data.addLast(node.val);
                }else{
                    data.addFirst(node.val);
                }

                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }

            result.add(new ArrayList<>(data));
            dirRight = !dirRight;
            size = queue.size();
        }
        return result;
    }

    /**
     * dfs方法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了95.64%的用户
     */
    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        if(root == null) return result;
        dfs(root,0,true);

        return result;
    }
    public void dfs(TreeNode root,int level,boolean dirRight) {
        if(root==null) return;
        if(result.size() == level) result.add(new LinkedList<>());

        LinkedList<Integer> datas = (LinkedList)result.get(level);
        if(dirRight) datas.addLast(root.val);
        else datas.addFirst(root.val);

        dfs(root.left,level+1,!dirRight);
        dfs(root.right,level+1,!dirRight);
    }
}
