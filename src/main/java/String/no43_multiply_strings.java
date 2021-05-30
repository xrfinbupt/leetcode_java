package String;

/**
 * 43. 字符串相乘
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 */
public class no43_multiply_strings {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int m = num1.length();
        int n = num2.length();
        int array1[] = new int[num1.length()];
        for (int i = 0; i < m; i++) array1[i] = num1.charAt(i) - '0';

        int array2[] = new int[num2.length()];
        for (int i = 0; i < n; i++) array2[i] = num2.charAt(i) - '0';

        int result[] = new int[num1.length() + num2.length()];
        for (int i = n - 1; i >= 0; i--) {
            int pos = m + i;
            for (int j = m - 1; j >= 0; j--) {
                int sum = array2[i] * array1[j];
                result[pos] = result[pos] + sum;
                if (result[pos] > 9) {
                    result[pos - 1] = result[pos - 1] + result[pos] / 10;
                    result[pos] = result[pos] % 10;
                }
                pos--;
            }
        }

        StringBuilder sbulder = new StringBuilder();
        for (int i = m + n - 1; i >= 0; i--) {
            int sum = result[i];
            if (sum > 9) {
                result[i - 1] = result[i - 1] + sum / 10;
                result[i] = sum % 10;
            }
        }
        boolean flag = true;
        for (int i = 0; i < m + n; i++) {
            int sum = result[i];
            if (flag && sum == 0) continue;
            else flag = false;
            sbulder.append(sum);
        }

        return sbulder.toString();
    }
}
