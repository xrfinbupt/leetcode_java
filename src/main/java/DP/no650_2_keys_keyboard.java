package DP;

/**
 * 650. 只有两个键的键盘
 * <p>
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 * <p>
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 * <p>
 * 示例 1：
 * 输入：3
 * 输出：3
 * 解释：
 * 最初, 只有一个字符 'A'。
 * 第 1 步, 使用 Copy All 操作。
 * 第 2 步, 使用 Paste 操作来获得 'AA'。
 * 第 3 步, 使用 Paste 操作来获得 'AAA'。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：0
 *  
 * <p>
 * 提示：
 * 1 <= n <= 1000
 * 链接：https://leetcode-cn.com/problems/2-keys-keyboard
 *
 * @author xurongfei
 * @Date 2022/2/4
 */
public class no650_2_keys_keyboard {
    /**
     * 执行用时：28 ms, 在所有 Java 提交中击败了23.64%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了10.17%的用户
     */
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= i / 2; j++) {
                int k = i / j;
                if (k * j != i) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[j] + 1 + (i - j) / j);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        no650_2_keys_keyboard obj = new no650_2_keys_keyboard();
        int input = 5;
        int res = obj.minSteps(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 6;
        res = obj.minSteps(input);
        System.out.println("input=" + input + ",res=" + res);
    }
}
