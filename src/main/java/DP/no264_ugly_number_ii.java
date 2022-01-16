package DP;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author xurongfei
 * @Date 2022/1/16
 */
public class no264_ugly_number_ii {
    /**
     * 超出时间限制
     */
    public int nthUglyNumber1(int n) {
        HashSet<Integer> cache = new HashSet<>();
        cache.add(1);
        cache.add(2);
        cache.add(3);
        cache.add(5);
        int index = 0;
        int res = 0;
        int i = 0;
        while (i < n) {
            index++;
            if (index == 1) {
                res = index;
                i++;
                continue;
            }

            int two = index / 2;
            int three = index / 3;
            int five = index / 5;
            if (two * 2 == index && cache.contains(two) || three * 3 == index && cache.contains(three) ||
                    five * 5 == index && cache.contains(five)) {
                cache.add(index);
                res = index;
                i++;
            }
        }
        return res;
    }

    /**
     * 执行用时：47 ms, 在所有 Java 提交中击败了30.79%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了21.15%的用户
     */
    public int nthUglyNumber2(int n) {
        if (n == 1) return 1;

        PriorityQueue<Long> queue = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) (o1 - o2);
            }
        });
        HashSet<Long> cache = new HashSet<>();
        cache.add(1L);
        queue.add(1L);

        long top = 1;
        while (n-- > 0) {
            top = queue.poll();
            long two = top * 2;
            if (!cache.contains(two) && (two <= 2147483647L)) { //2147483647
                //if (!cache.contains(two) ) {
                queue.add(two);
                cache.add(two);
            }
            long three = top * 3;
            if (!cache.contains(three) && (three <= 2147483647L)) {
                //if (!cache.contains(three)) {
                queue.add(three);
                cache.add(three);
            }
            long five = top * 5;
            if (!cache.contains(five) && (five <= 2147483647L)) {
                //if (!cache.contains(five) ) {
                queue.add(five);
                cache.add(five);
            }
        }

        return (int) top;
    }

    /**
     * dp + 三指针
     *
     * 参考官方解答：https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.54%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了49.40%的用户
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n == 1) return 1;

        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;

            dp[i] = Math.min(num2, Math.min(num3, num5));
            if (dp[i] == num2) p2++;
            if (dp[i] == num3) p3++;
            if (dp[i] == num5) p5++;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        no264_ugly_number_ii obj = new no264_ugly_number_ii();
        int input = 20;
        int res = obj.nthUglyNumber(input);
        System.out.println("input=" + input + ",res=" + res);
        input = 10;
        res = obj.nthUglyNumber(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 160;
        res = obj.nthUglyNumber(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 1390;
        res = obj.nthUglyNumber(input);
        System.out.println("input=" + input + ",res=" + res);

        input = 1690;
        res = obj.nthUglyNumber(input);
        System.out.println("input=" + input + ",res=" + res);
    }
}
