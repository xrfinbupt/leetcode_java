package String;

/**
 * 10. 正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *  
 * 示例 1：
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4：
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5：
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 * 提示：
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 */
public class no10_regular_expression_matching {
    boolean result = false;

    /**
     * 执行用时：65 ms, 在所有 Java 提交中击败了10.39%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了92.45%的用户
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        result = false;
        // * 前面有字符 和 .
        // . 不需要关心前面字符
        int sLen = s.length();
        int pLen = p.length();
        if(sLen == pLen && sLen == 0) return true;

        sub_isMatch(s, p, sLen, pLen, 0, 0, -1);
        if (result) return result;

        return false;
    }
    // p=ab*  ab.        ab*c
    // s=a ab abb abbb   ac
    public void sub_isMatch(String s, String p, int sLen, int pLen, int sPos, int pPos, int width) {
        if(pPos >= pLen && sPos >=sLen ) result = true;
        if(pPos >= pLen || result) return;

        char laterCh = 0;
        if (pPos < pLen - 1) laterCh = p.charAt(pPos + 1);
        char pCurr = p.charAt(pPos);
        if (laterCh == '*') {
            if (width == -1) {
                int w = 0;
                while (sPos + w <= sLen) {
                    if (result) return;

                    sub_isMatch(s, p, sLen, pLen, sPos, pPos, w);
                    w++;
                }
            } else if (width == 0) {
                sub_isMatch(s, p, sLen, pLen, sPos, pPos + 2, -1);
            } else {
                // width >= 1
                int w = 0;
                while (w < width) {
                    char ch2 = s.charAt(sPos + w);
                    if (ch2 == pCurr || pCurr == '.') w++;
                    else {
                        break;
                    }
                }
                if (w == width) sub_isMatch(s, p, sLen, pLen, sPos + width, pPos + 2, -1);
            }
        } else {
            char sCurr = 0;
            if(sPos<sLen) sCurr = s.charAt(sPos);

            if (pCurr == '.'&& sCurr!=0 || sCurr == pCurr) {
                sub_isMatch(s, p, sLen, pLen, sPos + 1, pPos + 1, -1);
            }
        }
    }

    public static void main(String args[]){
        no10_regular_expression_matching obj = new no10_regular_expression_matching();
        System.out.println("\"\",\"\"="+obj.isMatch("",""));
        System.out.println("\"\",\".\"="+obj.isMatch("","."));
        System.out.println("\"a\",\"a.\"="+obj.isMatch("a","a."));
        System.out.println("\"a\",\"ab*\"="+obj.isMatch("a","ab*"));
        System.out.println("\"ab\",\"ab*\"="+obj.isMatch("ab","ab*"));
        System.out.println("\"abb\",\"ab*\"="+obj.isMatch("abb","ab*"));
        System.out.println("\"abbb\",\"ab*\"="+obj.isMatch("abbb","ab*"));
        System.out.println("\"abb\",\"ab.\"="+obj.isMatch("abb","ab."));
        System.out.println("\"abc\",\"ab*c\"="+obj.isMatch("abc","ab*c"));
        System.out.println("\"ac\",\"ab*c\"="+obj.isMatch("ac","ab*c"));

        System.out.println("\"aa\",\"a\"="+obj.isMatch("aa","a"));
        System.out.println("\"aa\",\"a*\"="+obj.isMatch("aa","a*"));
        System.out.println("\"ab\",\".*\"="+obj.isMatch("ab",".*"));
        System.out.println("\"aab\",\"c*a*b\"="+obj.isMatch("aab","c*a*b"));
        System.out.println("\"mississippi\",\"mis*is*p*.\"="+obj.isMatch("mississippi","mis*is*p*."));
        System.out.println("\"ab\",\"..\"="+obj.isMatch("ab",".."));
    }
}
