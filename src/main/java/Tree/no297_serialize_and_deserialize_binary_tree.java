package Tree;

import com.sun.jmx.remote.internal.ArrayQueue;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * 297. 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
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
 * 提示：
 * 树中结点数在范围 [0, 10^4] 内
 * -1000 <= Node.val <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 *
 */
public class no297_serialize_and_deserialize_binary_tree {

    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder strBuilder = new StringBuilder();
        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.pollFirst();
            if (strBuilder.length() == 0) {
                strBuilder.append("" + p.val);
            } else {
                strBuilder.append(":" + p.val);
            }

            if (p.val == 6666) continue;

            if (p.left != null) {
                queue.addLast(p.left);
            } else {
                queue.addLast(new TreeNode(6666));
            }
            if (p.right != null) {
                queue.addLast(p.right);
            } else {
                queue.addLast(new TreeNode(6666));
            }
        }
        return strBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;

        String[] dataArray = data.split(":");
        int index = 0;
        TreeNode root = new TreeNode(Integer.valueOf(dataArray[index++]));
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.remove();
            if (p.val == 6666) {
                continue;
            }

            int element = Integer.valueOf(dataArray[index++]);
            if (element != 6666) {
                p.left = new TreeNode(element);
                queue.add(p.left);
            }

            element = Integer.valueOf(dataArray[index++]);
            if (element != 6666) {
                p.right = new TreeNode(element);
                queue.add(p.right);
            }
        }

        return root;
    }

    public static void main(String args[]){
        no297_serialize_and_deserialize_binary_tree obj = new no297_serialize_and_deserialize_binary_tree();
        String data = obj.serialize(null);
        System.out.println(data);
        obj.deserialize(data);

        data = obj.serialize(new TreeNode(1));
        System.out.println(data);
        TreeNode result = obj.deserialize(data);

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        data = obj.serialize(root);
        System.out.println(data);
        result = obj.deserialize(data);
    }
}
