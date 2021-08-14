package lcof_offer;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 *
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 */
public class offer54_kthLargest {
    int result = 0,k=0;
    boolean flag = false;
    /**
     * 方法1 暴力法
     * 执行用时：5 ms, 在所有 Java 提交中击败了5.36%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public int kthLargest(TreeNode root, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        recursive_kthLargest(root,result);

        Collections.sort(result, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        return result.get(k-1);
    }
    public void recursive_kthLargest(TreeNode root,  ArrayList<Integer> result) {
        if(root == null) return;
        result.add(root.val);

        recursive_kthLargest(root.left,result);
        recursive_kthLargest(root.right,result);
        return ;
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了7.88%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public int kthLargest2(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        recursive_kthLargest2(root,queue);

        int i =1;
        while(i<k){
            queue.poll();
            i++;
        }
        return queue.peek();
    }

    public void recursive_kthLargest2(TreeNode root,  PriorityQueue<Integer> queue) {
        if(root == null) return;
        queue.add(root.val);

        recursive_kthLargest2(root.left,queue);
        recursive_kthLargest2(root.right,queue);
        return ;
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了7.88%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了64.53%的用户
     */
    public int kthLargest3(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        recursive_kthLargest3(root, queue, k);

        return queue.peek();
    }

    public void recursive_kthLargest3(TreeNode root, PriorityQueue<Integer> queue, int k) {
        if (root == null) return;
        if(queue.size()<k){
            queue.add(root.val);
        } else if (queue.size() == k && queue.peek() < root.val) {
            queue.poll();
            queue.add(root.val);
        }

        recursive_kthLargest3(root.left, queue, k);
        recursive_kthLargest3(root.right, queue, k);
        return;
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了9.59%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了75.09%的用户
     */
    public int kthLargest4(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        recursive_kthLargest4(root, queue, k);

        if(flag) return result;
        else return queue.peek();
    }
    public void recursive_kthLargest4(TreeNode root, PriorityQueue<Integer> queue, int k) {
        if (root == null || flag) return;

        if (queue.size() < k) {
            queue.add(root.val);
        } else if (queue.size() == k && queue.peek() < root.val) {
            queue.poll();
            queue.add(root.val);
        } else {
            flag = true;
            result = queue.peek();
            return;
        }

        recursive_kthLargest4(root.right, queue, k);
        recursive_kthLargest4(root.left, queue, k);

        return;
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了93.10%的用户
     *
     * 参考解答
     * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/solution/mian-shi-ti-54-er-cha-sou-suo-shu-de-di-k-da-jie-d/
     */
    public int kthLargest5(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return result;
    }
    private void dfs(TreeNode root) {
        if (root == null) return;
        if (k == 0) return;
        dfs(root.right);
        if (--k == 0) result = root.val;
        dfs(root.left);
    }
    public static void main(String arg[]){
        TreeNode root = new TreeNode(3);
        //root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        offer54_kthLargest obj = new offer54_kthLargest();
        int result = obj.kthLargest4(root,2);
        System.out.println(result);
    }
}
