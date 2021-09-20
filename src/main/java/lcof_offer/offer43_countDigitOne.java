package lcof_offer;

/**
 * @author xurongfei
 * @Date 2021/9/12
 * <p>
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 * <p>
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * <p>
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 * <p>
 * 限制：
 * 1 <= n < 2^31
 * 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
 * <p>
 * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 */
public class offer43_countDigitOne {
    int[] table = {1, 19, 280, 3700, 46000, 550000, 6400000, 73000000, 820000000};

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了67.42%的用户
     */
    public int countDigitOne(int n) {
        if (n < 1) return 0;
        if (n <= 9) return 1;

        int wid = getWidth(n);
        int sum = 0;

        // 如果输入的n为 xxxx 四位数
        // 这里通过查表分别计算 一位 二位 三位的sum
        for (int i = 1; i < wid; i++) {
            sum += table[i - 1];
        }

        if (wid <= 1) return sum;

        // 这里计算 1000 ~ xxxx 的sum
        int[] orgArray = getOrgArray(n, wid);
        for (int i = 1; i <= orgArray[0]; i++) {
            if (i == orgArray[0]) {
                int leftN = getMin(orgArray);
                if (i == 1) {
                    sum += (leftN + 1);
                }

                sum += countDigitOne(leftN);
            } else {
                if (i == 1) {
                    sum += getWidVal(wid - 1);
                }

                sum += (wid - 1) * getWidVal(wid - 2);
            }
        }

        return sum;
    }

    int[] getOrgArray(int n, int wid) {
        int[] orgArray = new int[wid];
        while (wid > 0) {
            orgArray[wid - 1] = n % 10;
            n = n / 10;
            wid--;
        }
        return orgArray;
    }

    int getWidth(int n) {
        int wid = 0;
        while (n > 0) {
            wid++;
            n = n / 10;
        }
        return wid;
    }

    int getMin(int[] orgArray) {
        int sum = 0;
        for (int i = 1; i < orgArray.length; i++) {
            sum = sum * 10 + orgArray[i];
        }
        return sum;
    }

    public static int getWidVal(int wid) {
        if (wid == 0) return 1;
        if (wid < 0) return 0;

        int sum = 1;
        while (wid-- >= 1) {
            sum *= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        offer43_countDigitOne obj = new offer43_countDigitOne();
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
