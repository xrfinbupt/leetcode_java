package DFS_BFS;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 399. 除法求值
 * <p>
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * <p>
 * 示例 1：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * <p>
 * 示例 2：
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * <p>
 * 示例 3：
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 *  
 * 提示：
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/evaluate-division
 */
public class no399_evaluate_division {
    Set<String> caseCache = new HashSet<>();
    Set<Integer> pathCache = new HashSet<>();
    boolean findFlag = false;
    double target = -1.0;

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了81.60%的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了22.37%的用户
     * <p>
     * 优化一下后：
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了11.42%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了93.98%的用户
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int len = queries.size();
        double[] result = new double[len];

        for (List<String> iter : equations) {
            caseCache.add(iter.get(0));
            caseCache.add(iter.get(1));
        }
        for (int i = 0; i < queries.size(); i++) {
            List<String> iter = queries.get(i);
            pathCache.clear();
            findFlag = false;
            target = -1.0;
            String start = iter.get(0);
            String end = iter.get(1);
            if (start.equals(end)) {
                if (caseCache.contains(start)) {
                    result[i] = 1.0;
                } else {
                    result[i] = -1.0;
                }
            } else {
                dfs(equations, values, 1, start, end);
                result[i] = target;
            }
        }
        return result;
    }

    private void dfs(List<List<String>> equations, double[] values, double preValue, String start, String end) {
        for (int i = 0; i < equations.size(); i++) {
            if (findFlag) return;

            List<String> iter = equations.get(i);
            String A = iter.get(0);
            String B = iter.get(1);
            if (pathCache.contains(i)) continue;
            if (A.equals(start)) {
                if (B.equals(end)) {
                    findFlag = true;
                    target = preValue * values[i];
                    return;
                }
                pathCache.add(i);
                dfs(equations, values, preValue * values[i], B, end);
                pathCache.remove(i);
            }
            if (findFlag) return;

            if (pathCache.contains(i)) continue;
            if (B.equals(start)) {
                if (A.equals(end)) {
                    findFlag = true;
                    target = preValue * (1 / values[i]);
                    return;
                }
                pathCache.add(i);
                dfs(equations, values, preValue * (1 / values[i]), A, end);
                pathCache.remove(i);
            }
        }
    }


    Map<String, List<Integer>> map1 = new HashMap<>();
    Map<String, List<Integer>> map2 = new HashMap<>();

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了81.60%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了7.25%的用户
     */
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int len = queries.size();
        double[] result = new double[len];

        for (int i = 0; i < equations.size(); i++) {
            List<String> iter = equations.get(i);
            String a = iter.get(0);
            String b = iter.get(1);
            List<Integer> dataList = map1.getOrDefault(a, new ArrayList<>());
            dataList.add(i);
            map1.put(a, dataList);

            dataList = map2.getOrDefault(b, new ArrayList<>());
            dataList.add(i);
            map2.put(b, dataList);
        }
        for (int i = 0; i < queries.size(); i++) {
            List<String> iter = queries.get(i);
            pathCache.clear();
            findFlag = false;
            target = -1.0;
            String start = iter.get(0);
            String end = iter.get(1);
            if (start.equals(end)) {
                if (map1.containsKey(start) || map2.containsKey(start)) {
                    result[i] = 1.0;
                } else {
                    result[i] = -1.0;
                }
            } else {
                dfs2(equations, values, 1, start, end);
                result[i] = target;
            }
        }
        return result;
    }

    private void dfs2(List<List<String>> equations, double[] values, double preValue, String start, String end) {
        List<Integer> posList = map1.get(start);
        if (posList != null) {
            for (Integer i : posList) {
                if (pathCache.contains(i)) continue;
                List<String> iter = equations.get(i);
                String next = iter.get(1);
                if (next.equals(end)) {
                    findFlag = true;
                    target = preValue * values[i];
                    return;
                } else {
                    pathCache.add(i);
                    dfs2(equations, values, preValue * values[i], next, end);
                    pathCache.remove(i);
                }
            }
        }
        List<Integer> posList2 = map2.get(start);
        if (posList2 != null) {
            for (Integer i : posList2) {
                if (pathCache.contains(i)) continue;

                List<String> iter = equations.get(i);
                String next = iter.get(0);
                if (next.equals(end)) {
                    findFlag = true;
                    target = preValue * (1.0 / values[i]);
                    return;
                } else {
                    pathCache.add(i);
                    dfs2(equations, values, preValue * (1.0 / values[i]), next, end);
                    pathCache.remove(i);
                }
            }
        }
    }

    public static void main(String args[]) {
        no399_evaluate_division obj = new no399_evaluate_division();
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));
        double[] result = obj.calcEquation(equations, values, queries);
        System.out.println(JSON.toJSONString(result));
        //输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        //输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]


        equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));
        values = new double[]{1.5, 2.5, 5.0};
        queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"), Arrays.asList("bc", "cd"), Arrays.asList("cd", "bc"));
        result = obj.calcEquation(equations, values, queries);
        System.out.println(JSON.toJSONString(result));
        //输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        //输出：[3.75000,0.40000,5.00000,0.20000]

        equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("c", "d"));
        values = new double[]{2, 3, 5.0};
        queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("ab", "bc"), Arrays.asList("a", "a"), Arrays.asList("ax", "c"), Arrays.asList("bc", "cd"), Arrays.asList("b", "d"), Arrays.asList("aa", "d"));
        result = obj.calcEquation(equations, values, queries);
        System.out.println(JSON.toJSONString(result));
        //输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        //输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000,-1.00000,15.00000,-1.00000]
    }
}
