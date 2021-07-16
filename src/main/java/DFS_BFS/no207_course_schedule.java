package DFS_BFS;

import java.util.*;

/**
 * 207. 课程表
 *
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *  
 * 提示：
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 * 链接：https://leetcode-cn.com/problems/course-schedule
 */
public class no207_course_schedule {
    boolean[] dp = null;

    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了21.42%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了32.49%的用户
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int len = prerequisites.length;
        if(len == 0) return true;

        dp = new boolean[numCourses];
        Map<Integer, Set<Integer>> dataMap = new HashMap<>();
        for(int i=0;i<len;i++){
            int[] inputData = prerequisites[i];
            Set<Integer> data = dataMap.getOrDefault(inputData[1],new HashSet<>());
            if(data.contains(inputData[0])){
                return false;
            }

            data = dataMap.getOrDefault(inputData[0],new HashSet<>());
            data.add(inputData[1]);
            dataMap.put(inputData[0],data);
        }

        boolean []path = new boolean[numCourses];
        for(Map.Entry<Integer,Set<Integer>> iter:dataMap.entrySet()){
            boolean res = dfs(dataMap,iter.getKey(),path);
            if(!res) {
                return false;
            }
            dp[iter.getKey()] = true;
        }
        return true;
    }

    /**
     * 参考官方解答
     * 本题是一道经典的「拓扑排序」问题。
     * 给定一个包含n 个节点的有向图G，我们给出它的节点编号的一种排列，如果满足：对于图G 中的任意一条有向边(u,v)，u 在排列中都出现在v 的前面。那么称该排列是图G 的「拓扑排序」
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.17%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了78.50%的用户
     */
    List<List<Integer>> edges = new ArrayList<>();
    int[] visited;
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int len = prerequisites.length;
        if(len == 0) return true;

        for(int i=0;i<numCourses;i++){
            edges.add(new ArrayList<>());
        }
        for(int[] iter:prerequisites){
            edges.get(iter[1]).add(iter[0]);
        }
        visited = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            if(visited[i]==0){
                if(!dfs2(i)){
                    return false;
                }
            }
        }

        return true;
    }
    private boolean dfs2(int key){
        visited[key] = 1;
        for(Integer iter:edges.get(key)){
            if(visited[iter]==0){
                if(!dfs2(iter)){
                    return false;
                }
            }else if(visited[iter]==1){
                return false;
            }
        }

        visited[key] =2;
        return true;
    }

    private boolean dfs(Map<Integer, Set<Integer>> dataMap,int key,boolean[] path){
        if(path[key]) {
            return false;
        }
        if(dp[key]) return true;
        if(!dataMap.containsKey(key)) return true;

        path[key]=true;
        for(Integer iter:dataMap.get(key)){
            boolean res = dfs(dataMap,iter,path);
            if(!res) return false;
        }
        path[key] = false;
        return true;
    }
    public static void main(String args[]){
        no207_course_schedule obj = new no207_course_schedule();
        boolean res = obj.canFinish2(100,new int[][]{{1,0},{2,0},{2,1},{3,1},{3,2},{4,2},{4,3},{5,3},{5,4},{6,4},{6,5},{7,5},{7,6},{8,6},{8,7},{9,7},{9,8},{10,8},{10,9},{11,9},{11,10},{12,10},{12,11},{13,11},{13,12},{14,12},{14,13},{15,13},{15,14},{16,14},{16,15},{17,15},{17,16},{18,16},{18,17},{19,17},{19,18},{20,18},{20,19},{21,19},{21,20},{22,20},{22,21},{23,21},{23,22},{24,22},{24,23},{25,23},{25,24},{26,24},{26,25},{27,25},{27,26},{28,26},{28,27},{29,27},{29,28},{30,28},{30,29},{31,29},{31,30},{32,30},{32,31},{33,31},{33,32},{34,32},{34,33},{35,33},{35,34},{36,34},{36,35},{37,35},{37,36},{38,36},{38,37},{39,37},{39,38},{40,38},{40,39},{41,39},{41,40},{42,40},{42,41},{43,41},{43,42},{44,42},{44,43},{45,43},{45,44},{46,44},{46,45},{47,45},{47,46},{48,46},{48,47},{49,47},{49,48},{50,48},{50,49},{51,49},{51,50},{52,50},{52,51},{53,51},{53,52},{54,52},{54,53},{55,53},{55,54},{56,54},{56,55},{57,55},{57,56},{58,56},{58,57},{59,57},{59,58},{60,58},{60,59},{61,59},{61,60},{62,60},{62,61},{63,61},{63,62},{64,62},{64,63},{65,63},{65,64},{66,64},{66,65},{67,65},{67,66},{68,66},{68,67},{69,67},{69,68},{70,68},{70,69},{71,69},{71,70},{72,70},{72,71},{73,71},{73,72},{74,72},{74,73},{75,73},{75,74},{76,74},{76,75},{77,75},{77,76},{78,76},{78,77},{79,77},{79,78},{80,78},{80,79},{81,79},{81,80},{82,80},{82,81},{83,81},{83,82},{84,82},{84,83},{85,83},{85,84},{86,84},{86,85},{87,85},{87,86},{88,86},{88,87},{89,87},{89,88},{90,88},{90,89},{91,89},{91,90},{92,90},{92,91},{93,91},{93,92},{94,92},{94,93},{95,93},{95,94},{96,94},{96,95},{97,95},{97,96},{98,96},{98,97},{99,97}});
        System.out.println(res);

        obj.edges.clear();
        res = obj.canFinish2(2,new int[][]{{1,0},{0,1}});
        System.out.println(res);

        obj.edges.clear();
        res = obj.canFinish2(2,new int[][]{{1,0}});
        System.out.println(res);
    }
}
