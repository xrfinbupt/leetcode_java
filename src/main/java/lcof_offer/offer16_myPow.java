package lcof_offer;

/**
 * 剑指 Offer 16. 数值的整数次方
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * <p>
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^-2 = 1/(2^2) = 1/4 = 0.25
 * <p>
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 * <p>
 * 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 */
public class offer16_myPow {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了66.21%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了61.98%的用户
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;
        if (x == 0) return 0;
        if (x == 1) return 1;
        if (x == -1) {
            if (n > 0) {
                if (n % 2 == 1) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                // n < 0
                int sign = -1;
                long newData = 0L - n;
                if (newData % 2 == 0) {
                    sign = 1;
                }
                if (newData > Integer.MAX_VALUE) {
                    newData = Integer.MAX_VALUE;
                }
                return sign * 1.0 / myPow(Math.abs(x), (int) newData);
            }
        } else {
            if (n < 0) {
                int sign = 1;
                if (x < 0) sign = -1;
                long newData = 0L - n;
                if (newData % 2 == 0) {
                    sign = 1;
                }
                if (newData > Integer.MAX_VALUE) {
                    newData = Integer.MAX_VALUE;
                }
                return sign * 1.0 / myPow(Math.abs(x), (int) newData);
            }
            int count = 1;
            double sum = 1;
            double tempSum = 1;
            while (count <= n) {
                tempSum = tempSum * (count == 1 ? x : tempSum);
                if (count == n) {
                    sum = sum * tempSum;
                    break;
                } else if (count < n) {
                    count = count << 1;
                    if (count > n) {
                        count = count >> 1;
                        sum = sum * tempSum;
                        tempSum = 1;

                        n = n - count;
                        count = 1;
                    }
                }

                if (Math.abs(tempSum) < 0.0000001) {
                    return 0.0;
                }
                if (Math.abs(tempSum) > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        offer16_myPow obj = new offer16_myPow();
        double x = 2.0;
        int n = 10;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 34.00515;
        n = -3;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 2;
        n = -2147483648;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = -1;
        n = -2147483648;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = -2.0;
        n = 2;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 3.0;
        n = 5;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 1.0;
        n = 50;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 1;
        n = -50;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 1;
        n = 0;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = -1.0;
        n = 50;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = -1;
        n = -50;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = -1;
        n = -11;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = -1;
        n = 11;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = -1;
        n = 0;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 0;
        n = 50;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 0.20;
        n = 0;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));


        x = 2;
        n = 9;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 2;
        n = 8;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 2;
        n = 10;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));

        x = 1;
        n = -2147483648;
        System.out.println(x + "^" + n + "=" + obj.myPow(x, n));
    }
}
