package String;

/**
 * 32. 最长有效括号
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 *
 * 提示：
 * 0 <= s.length <= 3 * 10^4
 * s[i] 为 '(' 或 ')'
 *
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 */
public class no32_longest_valid_parentheses {
    /**
     * 双向扫描一遍 取最大值
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }

        int result = 0;
        int left = 0, right = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                result = Math.max(result, right * 2);
            }
            if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;
        for (int i = len - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                result = Math.max(result, left * 2);
            }
            if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return result;
    }

    public static void main(String args[]){
        String str = "()(()";
        System.out.println("str:"+str + " result:"+reverse(str));
         str = ")()())";
        System.out.println("str:"+str + " result:"+reverse(str));
        str = ")((())";
        System.out.println("str:"+str + " result:"+reverse(str));
        str = "))))())()()(()";
        System.out.println("str:"+str + " result:"+reverse(str));
    }

    public static String reverse(String str){
        StringBuilder strBuilder = new StringBuilder();
        int len = str.length();
        for(int i=len-1;i>=0;i--){
            if(str.charAt(i)=='('){
                strBuilder.append(")");
            }else{
                strBuilder.append("(");
            }
        }
        return strBuilder.toString();
    }
}
