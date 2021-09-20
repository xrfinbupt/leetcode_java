package Recursion;

import lcof_offer.offer43_countDigitOne;

/**
 * @author xurongfei
 * @Date 2021/9/20
 * <p>
 * 233. 数字 1 的个数
 * <p>
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *  
 * 提示：
 * 0 <= n <= 109
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-digit-one
 */
public class no233_number_of_digit_one {
    int[] table = {1, 19, 280, 3700, 46000, 550000, 6400000, 73000000, 820000000};
    int preNum = 0;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了23.69%的用户
     */
    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        if (n < 10) return 1;
        preNum = 0;

        int sum = 0;
        int wid = getWidth(n);
        int localPreNum = preNum;
        for (int i = 1; i < wid; i++) {
            sum += table[i - 1];
        }

        for (int i = 1; i <= localPreNum; i++) {
            if (i == localPreNum) {
                int leftN = getLeft(n, localPreNum, wid);
                sum += countDigitOne(leftN);
                if (i == 1) {
                    sum += (leftN + 1);
                }
            } else {
                if (i == 1) {
                    sum += getWidthVal(wid - 1);
                }
                sum += (wid - 1) * getWidthVal(wid - 2);
            }
        }

        return sum;
    }

    private int getLeft(int n, int localPreNum, int wid) {
        int left = localPreNum;
        while (wid-- > 1) {
            left *= 10;
        }
        return n - left;
    }

    private int getWidthVal(int w) {
        if (w == 0) return 1;
        if (w < 0) return 0;

        int sum = 1;
        while (w-- > 0) {
            sum *= 10;
        }
        return sum;
    }

    private int getWidth(int n) {
        int w = 0;
        while (n > 0) {
            if (n < 10) preNum = n;

            w++;
            n = n / 10;
        }
        return w;
    }

    public static void main(String[] args) {
        no233_number_of_digit_one obj = new no233_number_of_digit_one();
        int input = 1;
        int res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 10;
        res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 100;
        res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 1000;
        res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 10000;
        res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 234;
        res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 1234;
        res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 4234;
        res = obj.countDigitOne(input);
        System.out.println("input=" + input + ",res=" + res);
    }
}
