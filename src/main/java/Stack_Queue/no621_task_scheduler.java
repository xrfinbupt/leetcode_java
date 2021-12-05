package Stack_Queue;

import java.util.*;

/**
 * 621. 任务调度器
 * <p>
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 示例 1：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * <p>
 * 示例 2：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * <p>
 * 示例 3：
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= task.length <= 10^4
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 *
 * @author xurongfei
 * @Date 2021/12/5
 */
public class no621_task_scheduler {

    /**
     * 执行用时：128 ms, 在所有 Java 提交中击败了5.03%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了22.94%的用户
     */
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        int len = tasks.length;
        int[] valArray = new int[26];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else if (o1[2] != o2[2]) {
                    return o2[2] - o1[2];
                }
                return 0;
            }
        });

        PriorityQueue<int[]> queue2 = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else if (o1[2] != o2[2]) {
                    return o2[2] - o1[2];
                }
                return 0;
            }
        });

        for (char ch : tasks) {
            int key = ch - 'A';
            valArray[key]++;
        }
        for (int i = 0; i < 26; i++) {
            int all = valArray[i];
            if (all == 0) continue;
            queue.add(new int[]{i, 0, all});
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            // take one key
            int[] temp = queue.poll();

            int all = temp[2];
            int waitCount = temp[1];
            result += waitCount;

            if (waitCount != 0) {
                while (!queue.isEmpty()) {
                    int[] dataItem = queue.poll();
                    if (dataItem[1] != 0) {
                        dataItem[1] = dataItem[1] - waitCount;
                    }
                    queue2.add(dataItem);
                }
                PriorityQueue<int[]> tempQueue = queue;
                queue = queue2;
                queue2 = tempQueue;
            }

            while (!queue.isEmpty()) {
                int[] dataItem = queue.poll();
                if (dataItem[1] != 0) {
                    dataItem[1] = dataItem[1] - 1;
                }
                queue2.add(dataItem);
            }
            PriorityQueue<int[]> tempQueue = queue;
            queue = queue2;
            queue2 = tempQueue;

            result++;
            if (all > 1) {
                temp[1] = n;
                temp[2] = all - 1;
                queue.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        no621_task_scheduler obj = new no621_task_scheduler();
        int res = obj.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
        System.out.println(res);

        res = obj.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0);
        System.out.println(res);

        res = obj.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 1);
        System.out.println(res);

        res = obj.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3);
        System.out.println(res);

        res = obj.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2);
        System.out.println(res);

        res = obj.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'}, 2);
        System.out.println(res);//12
    }
}
