package Other;

/**
 * 29. 两数相除
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 *
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 */
public class no29_divide_two_integers {
    /**
     * 暴力法
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        boolean flag = false;
        if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) {
            flag = true;
        }
        long dividend1 = dividend;
        long divisor1  = divisor;

        dividend1 = Math.abs(dividend1);
        divisor1 = Math.abs(divisor1);
        if (divisor1 == 1) {
            long result =  flag ? dividend1 : 0 - dividend1;
            if(result>Integer.MAX_VALUE || result < Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }
            return (int)result;
        }

        long count = 0;
        while (dividend1 >= 0) {
            count++;
            dividend1 = dividend1 - divisor1;
        }

        long result =  flag ? count - 1 : 1 - count;
        if(result>Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int)result;
    }
    public int divide2(int dividend, int divisor) {
        boolean flag = false;
        if (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) {
            flag = true;
        }
        long dividend1 = dividend;
        long divisor1  = divisor;

        dividend1 = Math.abs(dividend1);
        divisor1 = Math.abs(divisor1);
        if (divisor1 == 1) {
            long result =  flag ? dividend1 : 0 - dividend1;
            if(result>Integer.MAX_VALUE || result < Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }
            return (int)result;
        }

        long result =  sub_div(dividend1,divisor1);
        result = flag?result:0-result;

        if(result>Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int)result;
    }
    public long sub_div(long a,long b){
        if(a<b) return 0;
        if(a == b) return 1;

        long tb = b;
        long count = 1;
        while((tb+tb)<=a){
            count = count + count;
            tb = tb + tb;
        }
        return count + sub_div(a-tb,b);
    }
    public static void main(String args[]){
        no29_divide_two_integers obj = new no29_divide_two_integers();
        int res = obj.divide(-2147483648 ,-1);
        System.out.println(res);
    }
}
