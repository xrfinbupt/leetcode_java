package DFS_BFS;

import java.util.*;

/**
 * 332. 重新安排行程
 *
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 *
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 *
 * 示例 1：
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 *
 * 示例 2：
 * 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 *  
 * 提示：
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi 和 toi 由大写英文字母组成
 * fromi != toi
 *
 * 链接：https://leetcode-cn.com/problems/reconstruct-itinerary
 */
public class no332_reconstruct_itinerary {
    Map<String,List<String>> edges = new HashMap<>();
    HashMap<String,Integer> visited = new HashMap<>();
    List<String> result = new ArrayList<>();
    boolean findFlag = false;
    int targetLen = 0;

    /**
     * 执行用时：17 ms, 在所有 Java 提交中击败了10.58%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了66.29%的用户
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        findFlag = false;
        targetLen = tickets.size() + 1;
        for(List<String> edge:tickets){
            List<String> datas = edges.getOrDefault(edge.get(0),new ArrayList<>());
            datas.add(edge.get(1));
            edges.put(edge.get(0),datas);
            int count = visited.getOrDefault(edge.get(0)+edge.get(1),0);
            visited.put(edge.get(0)+edge.get(1),count+1);
        }
        for(String key:edges.keySet()){
            List<String> datas = edges.get(key);
            Collections.sort(datas);
        }
        dfs("JFK",0);

        return result;
    }

    /**
     * 参考官方解答
     *
     * 执行用时：7 ms, 在所有 Java 提交中击败了82.79%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了76.56%的用户
     */
    Map<String,PriorityQueue<String>> graph = new HashMap<>();
    public List<String> findItinerary2(List<List<String>> tickets) {
        for(List<String> ticket:tickets){
            String from = ticket.get(0),to = ticket.get(1);
            if(!graph.containsKey(from)){
                graph.put(from,new PriorityQueue<>());
            }
            graph.get(from).offer(to);
        }

        List<String> result = new LinkedList<>();
        dfs("JFK",result);
        Collections.reverse(result);

        return result;
    }
    private void dfs(String start,List<String> result){
        while(graph.containsKey(start) && graph.get(start).size()>0){
            String temp = graph.get(start).poll();
            dfs(temp,result);
        }
        result.add(start); // 这一步不好想
    }
    private void dfs(String start,int index){
        if(findFlag) return;

        result.add(index,start);
        if(index + 1 == targetLen){
            findFlag = true;
            return;
        }
        List<String> treeSet = edges.get(start);
        if(treeSet == null) return;

        for(String to:treeSet){
            String edge = start + to;
            int count = visited.get(edge);
            if(count==0) continue;

            visited.put(edge,count-1);
            dfs(to,index+1);
        }
    }
    public static void main(String args[]){
        no332_reconstruct_itinerary obj = new no332_reconstruct_itinerary();
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC","LHR"));
        tickets.add(Arrays.asList("JFK","MUC"));
        tickets.add(Arrays.asList("SFO","SJC"));
        tickets.add(Arrays.asList("LHR","SFO"));

        List<String> resultPath = obj.findItinerary(tickets);
        System.out.println(resultPath);

        //[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        obj.visited.clear();
        obj.edges.clear();
        obj.result.clear();
        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));
        resultPath = obj.findItinerary(tickets);
        System.out.println(resultPath);

        obj.visited.clear();
        obj.edges.clear();
        obj.result.clear();
        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","KUL"));
//[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        tickets.add(Arrays.asList("JFK","NRT"));
        tickets.add(Arrays.asList("NRT","JFK"));
        resultPath = obj.findItinerary2(tickets);
        System.out.println(resultPath);
        // ["JFK","NRT","JFK","KUL"]

        obj.visited.clear();
        obj.edges.clear();
        obj.result.clear();
        tickets = new ArrayList<>();
        //[["EZE","AXA"],["TIA","ANU"],["ANU","JFK"],["JFK","ANU"],["ANU","EZE"],["TIA","ANU"],["AXA","TIA"],["TIA","JFK"],["ANU","TIA"],["JFK","TIA"]]
        tickets.add(Arrays.asList("EZE","AXA"));
        tickets.add(Arrays.asList("TIA","ANU"));
        tickets.add(Arrays.asList("ANU","JFK"));
        tickets.add(Arrays.asList("JFK","ANU"));
        tickets.add(Arrays.asList("ANU","EZE"));
        tickets.add(Arrays.asList("TIA","ANU"));
        tickets.add(Arrays.asList("AXA","TIA"));
        tickets.add(Arrays.asList("TIA","JFK"));
        tickets.add(Arrays.asList("ANU","TIA"));
        tickets.add(Arrays.asList("JFK","TIA"));
        resultPath = obj.findItinerary(tickets);
        System.out.println(resultPath);
        // ["JFK","NRT","JFK","KUL"]
    }
}
