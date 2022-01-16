package WeeklyContest;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 5982. 解决智力问题
 * <p>
 * 给你一个下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [pointsi, brainpoweri] 。
 * <p>
 * 这个数组表示一场考试里的一系列题目，你需要 按顺序 （也就是从问题 0 开始依次解决），针对每个问题选择 解决 或者 跳过 操作。解决问题 i 将让你 获得  pointsi 的分数，但是你将 无法 解决接下来的 brainpoweri 个问题（即只能跳过接下来的 brainpoweri 个问题）。如果你跳过问题 i ，你可以对下一个问题决定使用哪种操作。
 * <p>
 * 比方说，给你 questions = [[3, 2], [4, 3], [4, 4], [2, 5]] ：
 * 如果问题 0 被解决了， 那么你可以获得 3 分，但你不能解决问题 1 和 2 。
 * 如果你跳过问题 0 ，且解决问题 1 ，你将获得 4 分但是不能解决问题 2 和 3 。
 * 请你返回这场考试里你能获得的 最高 分数。
 * <p>
 * 示例 1：
 * 输入：questions = [[3,2],[4,3],[4,4],[2,5]]
 * 输出：5
 * 解释：解决问题 0 和 3 得到最高分。
 * - 解决问题 0 ：获得 3 分，但接下来 2 个问题都不能解决。
 * - 不能解决问题 1 和 2
 * - 解决问题 3 ：获得 2 分
 * 总得分为：3 + 2 = 5 。没有别的办法获得 5 分或者多于 5 分。
 * <p>
 * 示例 2：
 * 输入：questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
 * 输出：7
 * 解释：解决问题 1 和 4 得到最高分。
 * - 跳过问题 0
 * - 解决问题 1 ：获得 2 分，但接下来 2 个问题都不能解决。
 * - 不能解决问题 2 和 3
 * - 解决问题 4 ：获得 5 分
 * 总得分为：2 + 5 = 7 。没有别的办法获得 7 分或者多于 7 分。
 *  
 * 提示：
 * 1 <= questions.length <= 10^5
 * questions[i].length == 2
 * 1 <= pointsi, brainpoweri <= 10^5
 * <p>
 * 链接：https://leetcode-cn.com/problems/solving-questions-with-brainpower
 */
public class no5892_solving_questions_with_brainpower {
    /**
     * 超时了
     */
    public long mostPoints1(int[][] questions) {
        long result = 0;

        PriorityQueue<long[]> queue = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return (int) (o1[0] - o2[0]);
            }
        });
        queue.add(new long[]{-1, 0});

        int len = questions.length;
        for (int i = 0; i < len; i++) {
            long currMax = 0;
            while (true) {
                long[] top = queue.isEmpty() ? null : queue.peek();
                if (top != null && top[0] < i) {
                    queue.remove();

                    currMax = Math.max(currMax, top[1]);

                    int[] question = questions[i];
                    long[] curr = new long[]{i + question[1], top[1] + question[0]};
                    if (curr[0] < len) {
                        queue.add(curr);
                    }

                    result = Math.max(result, curr[1]);
                } else {
                    long[] curr = new long[]{i, currMax};
                    queue.add(curr);

                    break;
                }
            }
            System.out.println("i=" + i + ",queue=" + JSON.toJSONString(queue) + ",result=" + result);
        }
        return result;
    }

    /**
     * 参考
     * https://leetcode-cn.com/problems/solving-questions-with-brainpower/solution/dao-xu-dp-by-endlesscheng-2qkc/
     *
     * @param questions
     * @return
     */
    public long mostPoints(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            int next = questions[i][1] + i + 1;
            if (next < len) {
                dp[i] = Math.max(dp[i + 1], questions[i][0] + dp[next]);
            } else {
                dp[i] = Math.max(dp[i + 1], questions[i][0]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        no5892_solving_questions_with_brainpower obj = new no5892_solving_questions_with_brainpower();

        int[][] input = new int[][]{{3, 2}, {4, 3}, {4, 4}, {2, 5}};
        long res = obj.mostPoints(input);
        System.out.println("input=" + JSON.toJSONString(input) + ",result=" + res);

        input = new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}};
        res = obj.mostPoints(input);
        System.out.println("input=" + JSON.toJSONString(input) + ",result=" + res);

        input = new int[][]{{1, 1}, {2, 2}};
        res = obj.mostPoints(input);
        System.out.println("input=" + JSON.toJSONString(input) + ",result=" + res);

        input = new int[][]{{80, 2}, {11, 44}, {14, 9}, {24, 4}, {42, 75}, {79, 92}, {47, 47}, {74, 83}, {91, 49}, {8, 64}, {96, 50}};

        res = obj.mostPoints1(input);
        System.out.println("input=" + JSON.toJSONString(input) + ",result=" + res);

        res = obj.mostPoints(input);
        System.out.println("input=" + JSON.toJSONString(input) + ",result=" + res);
    }
}
