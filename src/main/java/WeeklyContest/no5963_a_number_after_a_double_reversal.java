package WeeklyContest;

/**
 * 5963. 反转两次的数字
 * <p>
 * 反转 一个整数意味着倒置它的所有位。
 * <p>
 * 例如，反转 2021 得到 1202 。反转 12300 得到 321 ，不保留前导零 。
 * 给你一个整数 num ，反转 num 得到 reversed1 ，接着反转 reversed1 得到 reversed2 。如果 reversed2 等于 num ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：num = 526
 * 输出：true
 * 解释：反转 num 得到 625 ，接着反转 625 得到 526 ，等于 num 。
 * <p>
 * 示例 2：
 * 输入：num = 1800
 * 输出：false
 * 解释：反转 num 得到 81 ，接着反转 81 得到 18 ，不等于 num 。
 * <p>
 * 示例 3：
 * 输入：num = 0
 * 输出：true
 * 解释：反转 num 得到 0 ，接着反转 0 得到 0 ，等于 num 。
 * <p>
 * 提示：
 * 0 <= num <= 10^6
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no5963_a_number_after_a_double_reversal {
    public boolean isSameAfterReversals(int num) {
        if (num == 0) {
            return true;
        }

        String input = num + "";
        int len = input.length();
        for (int i = len - 1; i >= 0; i--) {
            char ch = input.charAt(i);
            if (ch == '0') {
                return false;
            } else {
                return true;
            }
        }

        return true;
    }
    public static void main(String[] args){
        no5963_a_number_after_a_double_reversal obj = new no5963_a_number_after_a_double_reversal();
        int input = 189;
        boolean res = obj.isSameAfterReversals(input);
        System.out.println("res="+res+",input="+input);

        input = 1800;
        res = obj.isSameAfterReversals(input);
        System.out.println("res="+res+",input="+input);

        input = Integer.MAX_VALUE;
        res = obj.isSameAfterReversals(input);
        System.out.println("res="+res+",input="+input);
    }
}
