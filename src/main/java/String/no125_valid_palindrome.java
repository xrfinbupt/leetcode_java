package String;

/**
 * 125. 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 */
public class no125_valid_palindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return true;
        s = s.trim();

        int i = 0;
        int len = s.length();
        int j = len - 1;
        while (i < j) {
            char l = isCharValid(s.charAt(i));
            while (l == 0) {
                i++;
                if (i == len) return true;
                l = isCharValid(s.charAt(i));
            }
            char r = isCharValid(s.charAt(j));
            while (r == 0) {
                j--;
                if (j == -1) return true;
                r = isCharValid(s.charAt(j));
            }
            if (l == r) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public char isCharValid(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch;
        }
        if (ch >= 'a' && ch <= 'z') {
            return ch;
        }
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch - ('A' - 'a'));
        }

        return 0;
    }

    public static void main(String args[]) {
        String input = "A man, a plan, a canal: Panama";
        no125_valid_palindrome obj = new no125_valid_palindrome();
        obj.isPalindrome(input);

        input = ".,";
        obj.isPalindrome(input);
    }
}
