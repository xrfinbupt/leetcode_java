package Topological_Sort;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 310. 最小高度树
 *
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 *  
 * 示例 1：
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 * 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 *
 * 示例 2：
 * 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * 输出：[3,4]
 *
 * 示例 3：
 * 输入：n = 1, edges = []
 * 输出：[0]
 *
 * 示例 4：
 * 输入：n = 2, edges = [[0,1]]
 * 输出：[0,1]
 *  
 * 提示：
 * 1 <= n <= 2 * 10^4
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * 所有 (ai, bi) 互不相同
 * 给定的输入 保证 是一棵树，并且 不会有重复的边
 *
 * 链接：https://leetcode-cn.com/problems/minimum-height-trees
 */
public class no310_minimum_height_trees {
    int minDeep = Integer.MAX_VALUE;
    List<Integer> result =new ArrayList<>();
    List<List<Integer>> edgeData = new ArrayList<>();
    int visited[] = null;

    /**
     * 这种方法超时了（输入的n太大）
     */
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        for(int i=0;i<n;i++){
            edgeData.add(new ArrayList<>());
        }
        for(int[] edge:edges){
            edgeData.get(edge[0]).add(edge[1]);
            edgeData.get(edge[1]).add(edge[0]);
        }

        for(int i=0;i<n;i++){
            visited = new int[n];
            int deepTemp = dfs(i,0);
            if(deepTemp == 0){
                result.clear();
                return result;
            } else if(deepTemp<minDeep){
                minDeep = deepTemp;
                result.clear();
                result.add(i);
            }else if(deepTemp == minDeep){
                result.add(i);
            }
        }

        return result;
    }

    HashMap<Integer,Integer> graphArray[];
    boolean []visited1;

    /**
     * 参考 https://leetcode-cn.com/problems/minimum-height-trees/solution/dfsji-yi-sou-suo-by-meetce-/
     * 执行用时：37 ms, 在所有 Java 提交中击败了13.27%的用户
     * 内存消耗：48 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        graphArray = new HashMap[n];
        for(int i=0;i<n;i++){
            graphArray[i] = new HashMap<>();
        }
        for(int[] edge:edges){
            graphArray[edge[0]].put(edge[1],-1);
            graphArray[edge[1]].put(edge[0],-1);
        }
        List<Integer> result = new ArrayList<>();
        int minDeep = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            visited1 = new boolean[n];
            int deepTemp = dfs(i);
            if(deepTemp<minDeep){
                minDeep = deepTemp;
                result.clear();
                result.add(i);
            }else if(deepTemp == minDeep){
                result.add(i);
            }
        }

        return result;
    }
    private int dfs(int i){
        visited1[i] = true;
        int maxDeep = Integer.MIN_VALUE;
        for(int v:graphArray[i].keySet()){
            if(visited1[v]) continue;

            int cacheDist = graphArray[i].get(v);
            if(cacheDist == -1){
                cacheDist = dfs(v);
                graphArray[i].put(v,cacheDist);
            }else{
                visited1[v] = true;
            }
            maxDeep = Math.max(maxDeep, cacheDist+1);
        }
        return maxDeep==Integer.MIN_VALUE?1:maxDeep;
    }

    private int dfs(int i,int deep){
        if(deep>minDeep) return deep;
        visited[i] = 1;
        int maxDeep = deep;
        for(int v:edgeData.get(i)){
            if(visited[v]==0) {
                int deepTemp = dfs(v, deep + 1);
                maxDeep = Math.max(maxDeep, deepTemp);
            }else if(visited[v]==1){
                //maxDeep = 0;
                //System.out.println(v);
            }
        }
        visited[i] = 2;
        return maxDeep;
    }

    public static void main(String args[]){
        no310_minimum_height_trees obj = new no310_minimum_height_trees();
        List<Integer> res = obj.findMinHeightTrees(4,new int[][]{{1,0},{1,2},{1,3}});
        System.out.println(JSON.toJSON(res));
    }
}
