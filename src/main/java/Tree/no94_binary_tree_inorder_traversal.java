package Tree;

import com.sun.jmx.remote.internal.ArrayQueue;
import common.TreeNode;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
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
 * 输出：[2,1]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 *
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *  
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 *
 */
public class no94_binary_tree_inorder_traversal {
    List<Integer> result = new ArrayList<>();

    /**
     * 递归方法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        subTask(root);
        return result;
    }

    public void subTask(TreeNode root){
        if(root != null){
            subTask(root.left);
            result.add(root.val);
            subTask(root.right);
        }
    }

    /**
     * 非递归 Stack
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root == null) return result;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        while(stack.size()>0){
            TreeNode temp = stack.pop();
            if(temp.right!=null) stack.push(temp.right);

            if(temp.left==null){
                result.add(temp.val);
            }else {
                TreeNode tempLeft = temp.left;
                temp.left = null;
                temp.right = null;
                stack.push(temp);

                stack.push(tempLeft);
            }
        }

        return result;
    }
}
