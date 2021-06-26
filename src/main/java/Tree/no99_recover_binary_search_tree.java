package Tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. 恢复二叉搜索树
 *
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 * 示例 1：
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 *
 * 示例 2：
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *
 * 提示：
 * 树上节点的数目在范围 [2, 1000] 内
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 */
public class no99_recover_binary_search_tree {
    List<TreeNode> data = new ArrayList<>();

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了61.67%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了54.19%的用户
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if(root==null) return ;
        dfs(root);

        int len = data.size();
        if(len==1) return;

        TreeNode first = data.get(0),last = data.get(len-1);
        for(int i=1;i<len;i++){
            TreeNode node = data.get(i);
            if(node.val > first.val)
                first = node;
            else{
                break;
            }
        }
        for(int i=len-2;i>=0;i--){
            TreeNode node = data.get(i);
            if(node.val < last.val)
                last = node;
            else{
                break;
            }
        }
        if(first!=last){
            int temp = last.val;
            last.val = first.val;
            first.val = temp;
        }
    }
    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        data.add(root);
        dfs(root.right);
    }

    /**
     * 方法2
     * 执行用时：3 ms, 在所有 Java 提交中击败了61.67%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了32.26%的用户
     */
    private TreeNode pre = null;
    private boolean preFlag = false;
    private TreeNode later = null;
    private boolean laterFlag = false;
    public void recoverTree2(TreeNode root) {
        if(root == null || root.left == null && root.right == null) return;
        dfs_left(root);
        dfs_right(root);
        System.out.println("preFlag:"+preFlag +",laterFlag:"+laterFlag);
        System.out.println("pre:"+pre.val +",later:"+later.val);
        if(preFlag && laterFlag) {
            int temp = pre.val;
            pre.val = later.val;
            later.val = temp;
        }
    }
    private void dfs_left(TreeNode root){
        if(root == null || preFlag) return;
        dfs_left(root.left);
        if(preFlag) return;

        if(pre!=null && pre.val >= root.val){
            preFlag = true;
        }else{
            pre = root;
        }
        dfs_left(root.right);
    }
    private void dfs_right(TreeNode root){
        if(root == null || laterFlag) return;
        dfs_right(root.right);
        if(laterFlag) return;

        if(later!=null && later.val <= root.val){
            laterFlag = true;
        }else{
            later = root;
        }
        dfs_right(root.left);
    }

    /**
     * 方法3
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了39.86%的用户
     */
    private TreeNode preNode = null;
    private boolean preNodeFlag = false;
    private TreeNode lastNode = null;
    private boolean lastNodeFlag = false;
    public void recoverTree3(TreeNode root) {
        if(root == null || root.left == null && root.right == null) return;
        dfs_left2(root);

        int temp = preNode.val;
        preNode.val = lastNode.val;
        lastNode.val = temp;
    }
    private void dfs_left2(TreeNode root){
        if(root == null) return;
        dfs_left2(root.left);

        if(!preNodeFlag){
            if(preNode!=null && preNode.val >= root.val){
                preNodeFlag = true;
                lastNode = root;
            }else{
                preNode = root;
            }
        }else if(!lastNodeFlag){
            if(lastNode!=null && lastNode.val >= root.val){
                lastNode = root;
            }
        }
        dfs_left2(root.right);
    }
    public static void main(String args[]){
        no99_recover_binary_search_tree obj = new no99_recover_binary_search_tree();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        obj.recoverTree3(root);
        System.out.println();
    }
}
