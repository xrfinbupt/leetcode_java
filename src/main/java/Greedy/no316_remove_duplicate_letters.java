package Greedy;

import java.util.*;

/**
 * 316. 去除重复字母
 * <p>
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * <p>
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * <p>
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * <p>
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 *
 * @author xurongfei
 * @Date 2021/12/19
 */
public class no316_remove_duplicate_letters {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了46.90%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了64.77%的用户
     */
    public String removeDuplicateLetters(String s) {
        int[] charInStack = new int[26];
        int[] charCount = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int key = ch - 'a';
            charCount[key]++;
        }
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = ch - 'a';

            if (stack.isEmpty()) {
                stack.add(ch);
                charInStack[index] = 1;
            } else {
                if (charInStack[index] == 0) {
                    Character top = stack.peek();
                    int topIndex = top - 'a';
                    while (top > ch) {
                        if(charCount[topIndex] > 1){
                            stack.pop();
                            charInStack[topIndex] = 0;
                            charCount[topIndex]--;

                            if (stack.isEmpty()) break;
                            top = stack.peek();
                            topIndex = top - 'a';
                        }else{
                            break;
                        }
                    }

                    stack.add(ch);
                    charInStack[index]=1;
                }else{
                    charCount[index]--;
                }
            }
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }

        return result;
    }

    public static void main(String[] args) {
        no316_remove_duplicate_letters obj = new no316_remove_duplicate_letters();
        String input = "bcabc";
        String res = obj.removeDuplicateLetters(input);
        System.out.println("input=" + input + " res=" + res);// abc

        input = "cbacdcbc";
        res = obj.removeDuplicateLetters(input);
        System.out.println("input=" + input + " res=" + res);// acdb

        input = "abacb";
        res = obj.removeDuplicateLetters(input);
        System.out.println("input=" + input + " res=" + res);// abc

//        input = "rusrbofeggbbkyuyjsrzornp";
//        res = obj.removeDuplicateLetters(input);
//        System.out.println("input=" + input + " res=" + res);

//        input = "dguwzizqszpbicdquakqws";//error
//        res = obj.removeDuplicateLetters(input);
//        System.out.println("input=" + input + " res=" + res);// dgiqszpbcuakw
//
//        input = "uwzizqszpbicdquakqws";// error
//        res = obj.removeDuplicateLetters(input);
//        System.out.println("input=" + input + " res=" + res);// iqszpbcduakw
//
        input = "rusrbofeggbbkyuyjsrzornpdguwzizqszpbicdquakqws";
        res = obj.removeDuplicateLetters(input);
        System.out.println("input=" + input + " res=" + res);// bfegkuyjorndiqszpcaw
        // bfegyjornizpcduakqws
    }
}
