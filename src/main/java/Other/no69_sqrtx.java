package Other;

import java.math.BigInteger;

/**
 * 69. x 的平方根
 *
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * 链接：https://leetcode-cn.com/problems/sqrtx
 */
public class no69_sqrtx {
    /**
     * 最开始使用的这种方式 后来发现想复杂了
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x < 1) return 0;
        int min = 1, max = x;
        BigInteger org = BigInteger.valueOf(x);
        while (min <= max) {
            int mid = min + (max - min) / 2;
            BigInteger midBI = BigInteger.valueOf(mid);
            BigInteger sumBI = BigInteger.valueOf(mid);

            sumBI = sumBI.multiply(midBI);
            int diff = sumBI.compareTo(org);
            if (diff == 0) return mid;
            else if (diff > 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return max;
    }
    public int mySqrt1(int x) {
        if (x < 1) return 0;
        int min = 1, max = x;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            long sum = (long)mid * mid;

            long diff = sum - x;
            if (diff == 0) return mid;
            else if (diff > 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return max;
    }

    public static void main(String args[]){
        no69_sqrtx obj = new no69_sqrtx();
        int sum = obj.mySqrt1(2147395599);
        System.out.println(sum);
        sum = obj.mySqrt(3);
        System.out.println(sum);
        sum = obj.mySqrt(4);
        System.out.println(sum);
        sum = obj.mySqrt(5);
        System.out.println(sum);
        sum = obj.mySqrt(8);
        System.out.println(sum);
        sum = obj.mySqrt(9);
        System.out.println(sum);
        sum = obj.mySqrt(31);
        System.out.println(sum);
    }
}
