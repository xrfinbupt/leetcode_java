package Greedy;

/**
 * 738. 单调递增的数字
 * <p>
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * <p>
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * <p>
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 * <p>
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 * <p>
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 * <p>
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 *
 * @author xurongfei
 * @Date 2021/12/19
 */
public class no738_monotone_increasing_digits {
    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了30.30%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了32.03%的用户
     */
    public int monotoneIncreasingDigits(int n) {
        String input = "" + n;
        int[] array = new int[input.length()];
        int lastVal = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = (input.charAt(i) - '0');
            if (i > 0 && array[i] < array[i - 1]) {
                array[i] = Math.min(array[i] + 10, 9);
                if (lastVal == 0) {
                    array[i - 1] = array[i - 1] - 1;
                }
                lastVal = array[i] + 1;
            }
        }

        while (true) {
            boolean flag = true;
            for (int i = 0; i < array.length; i++) {
                if (i > 0 && array[i] < array[i - 1]) {
                    array[i] = Math.min(array[i] + 10, 9);
                    array[i - 1] = array[i - 1] - 1;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }

        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = result * 10 + array[i];
        }

        return result;
    }

    public static void main(String[] args) {
        no738_monotone_increasing_digits obj = new no738_monotone_increasing_digits();
        int input = 10;
        int res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = 1234;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = 332;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = 551;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = 34563;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = 2;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = 0;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = Integer.MAX_VALUE;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);

        input = 999998;
        res = obj.monotoneIncreasingDigits(input);
        System.out.println("input=" + input + " result=" + res);
    }
}
