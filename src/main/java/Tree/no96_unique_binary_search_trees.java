package Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 96. 不同的二叉搜索树
 *
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= n <= 19
 *
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 */
public class no96_unique_binary_search_trees {
    private Map<String,Integer> cache = new HashMap<String,Integer>();
    private Map<Integer,Integer> dp = new HashMap<Integer,Integer>();
    /**
     * 不加cache 这种方法就超时了
     *
     * 执行用时：10 ms, 在所有 Java 提交中击败了5.06%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了5.23%的用户
     */
    public int numTrees(int n) {
        if(n<1) return 0;

        return numTrees(1,n);
    }

    /**
     * 参考 https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了63.74%的用户
     */
    public int numTrees2(int n) {
        if(n<1) return 0;

        return numTrees2(1,n);
    }

    public int numTrees(int start,int end) {
        if(start > end) return 1;

        String key = start + "-" + end;
        Integer cacheData = cache.get(key);
        if (cacheData != null) return cacheData;

        int result = 0;
        for(int i=start;i<=end;i++){
            int left = numTrees(start,i-1);
            int right = numTrees(i+1,end);
            result += left*right;
        }

        cache.put(key,result);

        return result;
    }
    public int numTrees2(int start,int end) {
        if(start > end) return 1;

        Integer key = end -  start;
        Integer cacheData = dp.get(key);
        if (cacheData != null) return cacheData;

        int result = 0;
        for(int i=start;i<=end;i++){
            int left = numTrees2(start,i-1);
            int right = numTrees2(i+1,end);
            result += left*right;
        }

        dp.put(key,result);

        return result;
    }
}
