package lcof_offer;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * <p>
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * <p>
 * 数值（按顺序）可以分成以下几个部分：
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * <p>
 * 小数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * <p>
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 * <p>
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 * <p>
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 * <p>
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 * <p>
 * 示例 4：
 * 输入：s = "    .1  "
 * 输出：true
 *  
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 */
public class offer20_isNumber {

    /**
     * 执行用时：17 ms, 在所有 Java 提交中击败了6.65%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了23.75%的用户
     */
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.contains(" ")) {
            return false;
        }
        int start = s.indexOf("e");
        int end = s.lastIndexOf("e");
        if(start!=end) return false;

        start = s.indexOf("E");
        end = s.lastIndexOf("E");
        if(start!=end) return false;

        String[] arraySplit = s.split("e|E");
        if (arraySplit.length > 2) {
            return false;
        } else if (arraySplit.length == 2) {
            return (isFloat(arraySplit[0]) || isNum(arraySplit[0])) && isNum(arraySplit[1]);
        } else {
            return isFloat(s) || isNum(s);
        }
    }

    private boolean isFloat(String input) {
        if (input == null || input.isEmpty()) return false;
        boolean symbolFlag = false;
        boolean dotFlag = false;
        boolean numFlag = false;
        char[] array = input.toCharArray();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            char ch = array[i];
            if (ch == '.') {
                if (!dotFlag) {
                    dotFlag = true;
                } else {
                    return false;
                }
            } else if (ch == '+' || ch == '-') {
                if (numFlag || dotFlag) {
                    return false;
                }
                if (!symbolFlag) {
                    symbolFlag = true;
                } else {
                    return false;
                }
            } else if (ch < '0' || ch > '9') {
                return false;
            } else {
                numFlag = true;
            }
        }

        return numFlag;
    }

    private boolean isNum(String input) {
        if (input == null || input.isEmpty()) return false;
        boolean symbolFlag = false;
        boolean numFlag = false;
        char[] array = input.toCharArray();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            char ch = array[i];
            if (ch == '+' || ch == '-') {
                if (numFlag) {
                    return false;
                }
                if (!symbolFlag) {
                    symbolFlag = true;
                } else {
                    return false;
                }
            } else if (ch < '0' || ch > '9') {
                return false;
            } else {
                numFlag = true;
            }
        }
        return numFlag;
    }

    public static void main(String[] args) {
        String input = "7e69e";
        String[] array = input.split("e");
        System.out.println(array);

        offer20_isNumber obj = new offer20_isNumber();
        String s = "123e123";
        boolean res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "4e+";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "0";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "e";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = ".";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "   .1  ";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "+100";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "-123";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "3.1416";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "-1E-16";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "0123";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);


        s = "12e";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "1a3.14";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "1.2.3";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "+-5";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "12e+5.4";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);

        s = "7e69e";
        res = obj.isNumber(s);
        System.out.println(s + " = " + res);
    }
}
