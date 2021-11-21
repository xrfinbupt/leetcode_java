package Stack_Queue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 394. 字符串解码
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * 链接：https://leetcode-cn.com/problems/decode-string
 *
 * @author xurongfei
 * @Date 2021/11/21
 */
public class no394_decode_string {

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了79.67%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了32.00%的用户
     */
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();

        int len = s.length();
        if (len < 2) {
            return s;
        }

        int prePos = 0;
        int preMod = getMod(s.charAt(0));
        for (int i = 1; i < len; i++) {
            char ch = s.charAt(i);
            int currMod = getMod(ch);
            if (currMod != preMod) {
                String preStr = s.substring(prePos, i);
                if (!preStr.equals("]")) {
                    stack.add(preStr);
                }
                if (ch == ']') {
                    process(stack);
                    prePos = i;
                    preMod = -1;
                }else {
                    prePos = i;
                    preMod = currMod;
                }
            }
            if (i == len - 1) {
                if (ch != ']') {
                    String preStr = s.substring(prePos, i + 1);
                    stack.add(preStr);
                }
            }
        }

        String res = "";
        while (stack.size() > 0) {
            res = stack.pop() + res;
        }

        return res;
    }

    private void process(Stack<String> stack) {
        String curr = "";
        while (true) {
            String popStr = stack.pop();
            if (popStr.equals("[")) {
                int num = Integer.valueOf(stack.pop());
                String temp = curr;
                curr = "";
                while (num-- > 0) {
                    curr = curr + temp;
                }
                break;
            } else {
                curr = popStr + curr;
            }
        }
        stack.add(curr);
    }

    private int getMod(char ch) {
        if (ch == '[') {
            // preMod = 1
            return 1;
        } else if (ch == ']') {
            // preMod = 2
            return 2;
        } else if (ch >= '0' && ch <= '9') {
            // preMod = 3
            return 3;
        } else {
            // string
            // preMod = 4
            return 4;
        }
    }

    public static void main(String[] args) {
        no394_decode_string obj = new no394_decode_string();
        String input = "3[a]2[bc]";
        String res = obj.decodeString(input);
        System.out.println("input:" + input + ",res=" + res);

        input = "3[a2[c]]";
        res = obj.decodeString(input);
        System.out.println("input:" + input + ",res=" + res);

        input = "2[abc]3[cd]ef";
        res = obj.decodeString(input);
        System.out.println("input:" + input + ",res=" + res);

        input = "abc3[cd]xyz";
        res = obj.decodeString(input);
        System.out.println("input:" + input + ",res=" + res);

        input = "2[cd2[xy1[v]]xyz]";
        res = obj.decodeString(input);
        System.out.println("input:" + input + ",res=" + res);
    }
}
