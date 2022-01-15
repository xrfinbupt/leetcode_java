package Other;

/**
 * 263. 丑数
 * <p>
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * 示例 1：
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 * <p>
 * 示例 2：
 * 输入：n = 8
 * 输出：true
 * 解释：8 = 2 × 2 × 2
 * <p>
 * 示例 3：
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 * <p>
 * 示例 4：
 * 输入：n = 1
 * 输出：true
 * 解释：1 通常被视为丑数。
 * <p>
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 * <p>
 * 链接：https://leetcode-cn.com/problems/ugly-number
 *
 * @author xurongfei
 * @Date 2022/1/15
 */
public class no263_ugly_number {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了73.66%的用户
     */
    public boolean isUgly(int n) {
        while (n != 0) {
            if (n == 1 ) {
                return true;
            }
            if(n == -1){
                return false;
            }
            int org = n;
            int two = org / 2;
            int three = org / 3;
            int five = org / 5;
            if (two * 2 == org) {
                n = two;
            } else if (three * 3 == org) {
                n = three;
            } else if (five * 5 == org) {
                n = five;
            } else {
                return false;
            }
        }
        return false;
    }
    public static void main(String []args){
        no263_ugly_number obj = new no263_ugly_number();
        int n = -2147483648;
        boolean res = obj.isUgly(n);
        System.out.println("res="+res);
        System.out.println("res="+Integer.MIN_VALUE);
    }
}
