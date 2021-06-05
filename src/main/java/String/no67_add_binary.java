package String;

/**
 * 67. 二进制求和
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * 链接：https://leetcode-cn.com/problems/add-binary
 */
public class no67_add_binary {
    public String addBinary(String a, String b) {
        if (a.equals("0")) return b;
        if (b.equals("0")) return a;

        if (a.length() < b.length()) {
            String temp = b;
            b = a;
            a = temp;
        }

        StringBuilder sBuilder = new StringBuilder();
        int i = 0, j = 0;
        int lena = a.length();
        int lenb = b.length();
        boolean flag = false;
        while (i < lena && j < lenb) {
            char a_ch = a.charAt(lena -1 - i);
            char b_ch = b.charAt(lenb - 1- j);
            if (a_ch == '1' && b_ch == '1') {
                if (flag) sBuilder.append("1");
                else sBuilder.append("0");

                flag = true;
            } else if (a_ch == '1' || b_ch == '1') {
                if (flag) {
                    sBuilder.append("0");
                    flag = true;
                } else sBuilder.append("1");
            } else {
                if (flag) sBuilder.append("1");
                else sBuilder.append("0");

                flag = false;
            }
            i++;
            j++;
        }

        while (i < lena) {
            char a_ch = a.charAt(lena - 1 - i);
            if (a_ch == '1') {
                if (flag) {
                    sBuilder.append("0");
                    flag = true;
                } else sBuilder.append("1");
            } else {
                if (flag) sBuilder.append("1");
                else sBuilder.append("0");

                flag = false;
            }
            i++;
        }
        if (flag) sBuilder.append("1");
        StringBuilder result = new StringBuilder();
        int len = sBuilder.length();
        for (int k = 0; k < len; k++) {
            result.append(sBuilder.charAt(len - 1 - k));
        }

        return result.toString();
    }

    public static void main(String args[]){
        no67_add_binary obj = new no67_add_binary();
        System.out.println("0,0="+obj.addBinary("0","0"));
        System.out.println("0,1="+obj.addBinary("0","1"));
        System.out.println("1,0="+obj.addBinary("1","0"));
        System.out.println("1,1="+obj.addBinary("1","1"));
        System.out.println("11,1="+obj.addBinary("11","1"));
        System.out.println("1010,1011="+obj.addBinary("1010","1011"));
        System.out.println("1010,1111="+obj.addBinary("1010","1111"));
    }
}
