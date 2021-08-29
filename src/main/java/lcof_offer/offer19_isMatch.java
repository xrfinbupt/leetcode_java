package lcof_offer;

/**
 * @author xurongfei
 * @Date 2021/8/29
 * <p>
 * 剑指 Offer 19. 正则表达式匹配
 * <p>
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * <p>
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
 * <p>
 * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
 */
public class offer19_isMatch {
    boolean result = false;

    /**
     * 执行用时：40 ms, 在所有 Java 提交中击败了11.66%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了92.21%的用户
     */
    public boolean isMatch(String s, String p) {
        result = false;
        if ((s == null || s.isEmpty()) && (p == null || p.isEmpty())) return true;
        char[] sArray = s.toCharArray();
        int sLen = sArray.length;
        char[] pArray = p.toCharArray();
        int pLen = pArray.length;

        isMatch(sArray, 0, sLen, pArray, 0, pLen, -1);
        return result;
    }

    public boolean isMatch(char[] sArray, int i, int sLen, char[] pArray, int j, int pLen, int width) {
        if (i >= sLen && j >= pLen) {
            result = true;
        }
        if (result) return result;
        if (j >= pLen) return result;

        char nextPChar = 0;
        if (j + 1 < pLen) nextPChar = pArray[j + 1];

        if (nextPChar == '*') {
            if (width == -1) {
                for (int w = 0; w <= sLen - i; w++) {
                    isMatch(sArray, i, sLen, pArray, j, pLen, w);
                    if (result) return result;
                }
            } else if (width == 0) {
                isMatch(sArray, i, sLen, pArray, j + 2, pLen, -1);
            } else {
                // width > 1
                int w = 0;
                while (w < width) {
                    char sCurr = sArray[i + w];
                    if (sCurr == pArray[j] || pArray[j] == '.') w++;
                    else break;
                }
                if (w == width) isMatch(sArray, i + w, sLen, pArray, j + 2, pLen, -1);
            }
        } else {
            char sCurr = 0;
            if (i < sLen) sCurr = sArray[i];

            if (pArray[j] == '.' && sCurr != 0 || pArray[j] == sCurr) {
                isMatch(sArray, i + 1, sLen, pArray, j + 1, pLen, -1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        offer19_isMatch obj = new offer19_isMatch();
        String input = "aa";
        String patten = "a";
        boolean result = obj.isMatch(input, patten);
        System.out.println("input:" + input + ",patten:" + patten + " = " + result);

        input = "a";
        patten = "ab*";
        result = obj.isMatch(input, patten);
        System.out.println("input:" + input + ",patten:" + patten + " = " + result);

        input = "aa";
        patten = "a*";
        result = obj.isMatch(input, patten);
        System.out.println("input:" + input + ",patten:" + patten + " = " + result);

        input = "aa";
        patten = "aa";
        result = obj.isMatch(input, patten);
        System.out.println("input:" + input + ",patten:" + patten + " = " + result);

        input = "ab";
        patten = ".*";
        result = obj.isMatch(input, patten);
        System.out.println("input:" + input + ",patten:" + patten + " = " + result);

        input = "aab";
        patten = "c*a*b";
        result = obj.isMatch(input, patten);
        System.out.println("input:" + input + ",patten:" + patten + " = " + result);

        input = "mississippi";
        patten = "mis*is*p*.";
        result = obj.isMatch(input, patten);
        System.out.println("input:" + input + ",patten:" + patten + " = " + result);
    }
}
