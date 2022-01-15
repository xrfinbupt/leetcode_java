package DP;

import java.math.BigInteger;

/**
 * 639. 解码方法 II
 * <p>
 * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" 对应分组 (1 1 10 6)
 * "KJF" 对应分组 (11 10 6)
 * 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
 * <p>
 * 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
 * <p>
 * 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
 * <p>
 * 由于答案数目可能非常大，返回对 10^9 + 7 取余 的结果。
 *  
 * 示例 1：
 * 输入：s = "*"
 * 输出：9
 * 解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
 * 可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
 * 因此，"*" 总共有 9 种解码方法。
 * <p>
 * 示例 2：
 * 输入：s = "1*"
 * 输出：18
 * 解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
 * 每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
 * 因此，"1*" 共有 9 * 2 = 18 种解码方法。
 * <p>
 * 示例 3：
 * 输入：s = "2*"
 * 输出：15
 * 解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
 * "21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
 * 因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
 *  
 * <p>
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 是 0 - 9 中的一位数字或字符 '*'
 * <p>
 * 链接：https://leetcode-cn.com/problems/decode-ways-ii
 *
 * @author xurongfei
 * @Date 2022/1/15
 */
public class no639_decode_ways_ii {
    /**
     * 超时了
     *
     * @param s
     * @return
     */
    public int numDecodings1(String s) {
        int len = s.length();
        BigInteger dp0 = new BigInteger("1");
        BigInteger dp1 = new BigInteger("1");

        BigInteger fifteen = new BigInteger("15");
        BigInteger six = new BigInteger("6");
        BigInteger nine = new BigInteger("9");
        for (int i = 0; i < len; i++) {
            BigInteger curr = null;
            if (s.charAt(i) == '*') {
                curr = (i > 0 ? dp1 : new BigInteger("1")).multiply(nine);
                if (i > 0) {
                    BigInteger last = null;
                    if (s.charAt(i - 1) == '*') {
                        last = (i > 1 ? dp0 : new BigInteger("1")).multiply(fifteen);
                    } else if (s.charAt(i - 1) == '1') {
                        last = (i > 1 ? dp0 : new BigInteger("1")).multiply(nine);
                    } else if (s.charAt(i - 1) == '2') {
                        last = (i > 1 ? dp0 : new BigInteger("1")).multiply(six);
                        ;
                    }
                    if (last != null) {
                        curr = curr.add(last);
                    }
                }
            } else {
                int currVal = s.charAt(i) - '0';
                if (currVal != 0) {
                    curr = i > 0 ? dp1 : new BigInteger("1");
                } else {
                    curr = new BigInteger("0");
                }
                if (i > 0) {
                    if (s.charAt(i - 1) == '*') {
                        if (currVal >= 0 && currVal <= 16) {
                            BigInteger last = i > 1 ? dp0 : new BigInteger("1");
                            curr = curr.add(last);
                        }
                        if (currVal >= 0 && currVal <= 6) {
                            BigInteger last = i > 1 ? dp0 : new BigInteger("1");
                            curr = curr.add(last);
                        }
                    } else {
                        int pre = s.charAt(i - 1) - '0';
                        if ((pre * 10 + currVal >= 10) && (pre * 10 + currVal <= 26)) {
                            BigInteger last = i > 1 ? dp0 : new BigInteger("1");
                            curr = curr.add(last);
                        }
                    }
                }
            }
            dp0 = dp1;
            dp1 = curr;
        }
        long mod = 1000000000L + 7;
        BigInteger res = dp1.mod(new BigInteger(mod + ""));
        return res.intValue();
    }

    /**
     * 参考官方解答
     * 执行用时：20 ms, 在所有 Java 提交中击败了37.76%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了96.94%的用户
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int len = s.length();
        long dp0 = 0;
        long dp1 = 0;

        long mod = 1000000000L + 7;
        for (int i = 0; i < len; i++) {
            long curr = 0;
            if (s.charAt(i) == '*') {
                curr = (i > 0 ? dp1 : 1) * 9;
                if (i > 0) {
                    if (s.charAt(i - 1) == '*') {
                        curr += (i > 1 ? dp0 : 1) * 15;
                    } else if (s.charAt(i - 1) == '1') {
                        curr += (i > 1 ? dp0 : 1) * 9;
                    } else if (s.charAt(i - 1) == '2') {
                        curr += (i > 1 ? dp0 : 1) * 6;
                    }
                }
            } else {
                int currVal = s.charAt(i) - '0';
                if (currVal != 0) {
                    curr = i > 0 ? dp1 : 1;
                }
                if (i > 0) {
                    if (s.charAt(i - 1) == '*') {
                        if (currVal >= 0 && currVal <= 16) {
                            curr += i > 1 ? dp0 : 1;
                        }
                        if (currVal >= 0 && currVal <= 6) {
                            curr += i > 1 ? dp0 : 1;
                        }
                    } else {
                        int pre = s.charAt(i - 1) - '0';
                        if ((pre * 10 + currVal >= 10) && (pre * 10 + currVal <= 26)) {
                            curr += i > 1 ? dp0 : 1;
                        }
                    }
                }
            }
            dp0 = dp1;
            dp1 = curr % mod;
        }

        return (int) dp1;
    }

    public static void main(String[] args) {
        no639_decode_ways_ii obj = new no639_decode_ways_ii();
        String input = "1*";
        int res = obj.numDecodings(input);
        System.out.println(res);

        input = "**";
        res = obj.numDecodings(input);
        System.out.println(res);

        input = "910";
        res = obj.numDecodings(input);
        System.out.println(res);

        input = "210";
        res = obj.numDecodings(input);
        System.out.println(res);

        input = "*0";
        res = obj.numDecodings(input);
        System.out.println(res);
    }
}
