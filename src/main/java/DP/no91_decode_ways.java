package DP;

/**
 * 91. 解码方法
 *
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 *
 * 示例 4：
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 *  
 *
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 *
 * 链接：https://leetcode-cn.com/problems/decode-ways
 *
 * @author xurongfei
 * @Date 2022/1/9
 */
public class no91_decode_ways {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了28.44%的用户
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            int curr = s.charAt(i) - '0';
            if (curr != 0) {
                dp[i] = i > 0 ? dp[i - 1] : 1;
            }
            if (i > 0) {
                int pre = s.charAt(i - 1) - '0';
                if (pre != 0 && pre * 10 + curr <= 26) {
                    dp[i] += i > 1 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        no91_decode_ways obj = new no91_decode_ways();
        String input = new String("11106");
        int res = obj.numDecodings(input);
        System.out.println("res=" + res);

        input = new String("016");
        res = obj.numDecodings(input);
        System.out.println("res=" + res);
    }
}
