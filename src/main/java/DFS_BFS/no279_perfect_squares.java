package DFS_BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 279. 完全平方数
 * <p>
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * <p>
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *  
 * 提示：
 * 1 <= n <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 */
public class no279_perfect_squares {
    private boolean flag = false;

    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了95.08%的用户
     * 内存消耗：37.5 MB, 在所有 Java 提交中击败了59.87%的用户
     */
    public int numSquares(int n) {
        List<Integer> datas = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        flag = false;

        int i = 1;
        while (true) {
            int sum = i * i;
            if (sum > n) break;
            datas.add(sum);
            set.add(sum);
            i++;
        }

        i = 1;
        while (i <= n) {
            dfs(datas, set, i, n);
            if (flag) return i;
            i++;
        }
        return n;
    }

    private void dfs(List<Integer> datas, Set<Integer> set, int maxCount, int n) {
        if (maxCount == 0) return;
        else if (maxCount == 1) {
            if (set.contains(n)) {
                flag = true;
                return;
            }
        } else {
            for (Integer iter : datas) {
                if (flag) return;
                if (n - iter > 0) {
                    dfs(datas, set, maxCount - 1, n - iter);
                }
            }
        }
    }

    public static void main(String args[]) {
        no279_perfect_squares obj = new no279_perfect_squares();
        int input = 12;
        int res = obj.numSquares(input);
        System.out.println(input + " = " + res);

        input = 13;
        res = obj.numSquares(input);
        System.out.println(input + " = " + res);

        input = 130;
        res = obj.numSquares(input);
        System.out.println(input + " = " + res);

        input = 1;
        res = obj.numSquares(input);
        System.out.println(input + " = " + res);

        input = 2;
        res = obj.numSquares(input);
        System.out.println(input + " = " + res);

        input = 43;
        res = obj.numSquares(input);
        System.out.println(input + " = " + res);
    }
}
