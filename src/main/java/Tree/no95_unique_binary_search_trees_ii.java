package Tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 *
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 8
 *
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 */
public class no95_unique_binary_search_trees_ii {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.43%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了64.14%的用户
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();

        if(n==1){
            result.add(new TreeNode(1));
            return result;
        }else{
            List<TreeNode> preResult = generateTrees(n-1);

            for(TreeNode iterRoot:preResult){
                int index = 1;
                boolean flag = true;
                while(index <= n-1 && flag) {
                    if(index==1) {
                        TreeNode node = new TreeNode(n);
                        TreeNode newRoot = clone(iterRoot);
                        node.left = newRoot;
                        result.add(node);

                        if(newRoot.right==null) {
                            newRoot = clone(iterRoot);
                            newRoot.right = new TreeNode(n);
                            result.add(newRoot);
                        }
                    }else{
                        TreeNode newRoot = clone(iterRoot);
                        TreeNode pre = null;
                        TreeNode p = newRoot;
                        for (int i = 1; i < index; i++) {
                            pre = p;
                            p = p.right;
                            if(p==null) break;
                        }
                        if(p!=null){
                            TreeNode temp = p.right;
                            TreeNode curr = new TreeNode(n);
                            pre.right = curr;
                            curr.left = p;
                            result.add(newRoot);

                            if(temp ==null){
                                newRoot = clone(iterRoot);
                                p = newRoot;
                                for (int i = 1; i < index; i++) {
                                    pre = p;
                                    p = p.right;
                                    if(p==null) break;
                                }
                                if(p!=null){
                                    curr = new TreeNode(n);
                                    p.right = curr;
                                    result.add(newRoot);
                                }
                            }
                        }else {
                            flag = false;
                        }
                    }
                    index++;
                }
            }

            return result;
        }
    }

    /**
     * 重构一版 代码清晰点
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.43%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了14.12%的用户
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees_refactor(int n) {
        List<TreeNode> result = new ArrayList<>();

        if(n==1){
            result.add(new TreeNode(1));
            return result;
        }else{
            List<TreeNode> preResult = generateTrees(n-1);
            for(TreeNode iterRoot:preResult){
                int index = 1;
                while(true) {
                    TreeNode newRoot = clone(iterRoot);
                    TreeNode[] nodes = findRightNode(newRoot,index);
                    if(nodes ==null){
                        break;
                    }
                    TreeNode pre = nodes[1];
                    TreeNode curr =  nodes[0];
                    boolean insertNodeFlag = curr.right==null?true:false;

                    TreeNode insertNode = new TreeNode(n);
                    if(pre==null){
                        insertNode.left = curr;
                        result.add(insertNode);
                    }else{
                        pre.right = insertNode;
                        insertNode.left = curr;
                        result.add(newRoot);
                    }

                    if(insertNodeFlag){
                        newRoot = clone(iterRoot);
                        nodes = findRightNode(newRoot,index);
                        curr = nodes[0];
                        insertNode = new TreeNode(n);
                        curr.right = insertNode;
                        result.add(newRoot);
                    }

                    index++;
                }
            }

            return result;
        }
    }
    private TreeNode[] findRightNode(TreeNode root,int k){
        TreeNode pre = null;
        TreeNode curr = root;
        for (int i = 1; i < k; i++) {
            pre = curr;
            curr = curr.right;
            if(curr==null) break;
        }
        if(curr==null) return null;
        return new TreeNode[]{curr,pre};
    }
    private TreeNode clone(TreeNode root){
        if(root==null) return null;

        TreeNode myroot = new TreeNode(root.val);
        myroot.left = clone(root.left);
        myroot.right = clone(root.right);

        return myroot;
    }

    public static void main(String args[]){
        no95_unique_binary_search_trees_ii obj = new no95_unique_binary_search_trees_ii();
        List<TreeNode> result = obj.generateTrees(2);
        System.out.println(result.size());
    }
}
