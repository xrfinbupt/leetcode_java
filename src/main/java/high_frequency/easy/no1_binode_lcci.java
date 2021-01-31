package high_frequency.easy;

import com.alibaba.fastjson.JSON;
import common.TreeNode;

/**
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。
 * 注意：本题相对原题稍作改动
 *
 *
 * 示例：
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * 提示：
 *
 * 节点数量不会超过 100000。
 * https://github.com/azl397985856/leetcode/blob/master/problems/binode-lcci.md
 */
public class no1_binode_lcci {
    TreeNode first = null;

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;

        TreeNode tail = null;
        if (root.left == null) first = root;

        if (root.left != null) {
            tail = DFS(root.left, null);
            tail.right = root;
            root.left = null;
        }
        if (root.right != null) {
            DFS(root.right, root);
        }

        return first;
    }

    /**
     * 重点是记录pre记录
     * https://www.suxieban.com/index.html# 画二叉树图 好分析
     *
     * @param root
     * @param pre  二叉树的前驱节点  pre.right很重要
     * @return 返回二叉树最后一个节点
     */
    public TreeNode DFS(TreeNode root, TreeNode pre) {
        if (first == null && root != null && root.left == null) {
            first = root;
        }
        TreeNode tail1 = null;
        if (root.left != null) {
            tail1 = DFS(root.left, pre);

            tail1.right = root;
            root.left = null;
        } else if (root.left == null && pre != null) {
            pre.right = root;
        }

        TreeNode tail2 = root;
        if (root.right != null) {
            tail2 = DFS(root.right, root);
        }

        return tail2;
    }

    public static void main(String args[]) {
        Integer[] array = new Integer[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        TreeNode root = TreeNode.getTreeNode(array);
        System.out.println(root);

        no1_binode_lcci app = new no1_binode_lcci();
        TreeNode result = app.convertBiNode(root);
        System.out.println(JSON.toJSONString(result));
    }
}