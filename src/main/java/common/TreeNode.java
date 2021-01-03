package common;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode right;
    public TreeNode left;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode getTreeNode(Integer[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(nums[0]);
        LinkedList<TreeNode> subTree = new LinkedList<>();
        subTree.push(head);
        for (int i = 1; i < nums.length; i++) {
            if (!subTree.isEmpty()) {
                TreeNode cur = subTree.pop();
                if (nums[i] != null) {
                    cur.left = new TreeNode(nums[i]);
                    subTree.add(cur.left);
                }
                i++;
                if (i >= nums.length) {
                    break;
                }
                if (nums[i] != null) {
                    cur.right = new TreeNode(nums[i]);
                    subTree.add(cur.right);
                }

            } else {
                break;
            }
        }
        return head;
    }
}