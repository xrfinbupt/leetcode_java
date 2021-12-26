package WeeklyContest;

import com.alibaba.fastjson.JSON;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 5965. 相同元素的间隔之和
 * <p>
 * 给你一个下标从 0 开始、由 n 个整数组成的数组 arr 。
 * arr 中两个元素的 间隔 定义为它们下标之间的 绝对差 。更正式地，arr[i] 和 arr[j] 之间的间隔是 |i - j| 。
 * 返回一个长度为 n 的数组 intervals ，其中 intervals[i] 是 arr[i] 和 arr 中每个相同元素（与 arr[i] 的值相同）的 间隔之和 。
 * 注意：|x| 是 x 的绝对值。
 * <p>
 * 示例 1：
 * 输入：arr = [2,1,3,1,2,3,3]
 * 输出：[4,2,7,2,4,4,5]
 * 解释：
 * - 下标 0 ：另一个 2 在下标 4 ，|0 - 4| = 4
 * - 下标 1 ：另一个 1 在下标 3 ，|1 - 3| = 2
 * - 下标 2 ：另两个 3 在下标 5 和 6 ，|2 - 5| + |2 - 6| = 7
 * - 下标 3 ：另一个 1 在下标 1 ，|3 - 1| = 2
 * - 下标 4 ：另一个 2 在下标 0 ，|4 - 0| = 4
 * - 下标 5 ：另两个 3 在下标 2 和 6 ，|5 - 2| + |5 - 6| = 4
 * - 下标 6 ：另两个 3 在下标 2 和 5 ，|6 - 2| + |6 - 5| = 5
 * <p>
 * 示例 2：
 * 输入：arr = [10,5,10,10]
 * 输出：[5,0,3,4]
 * 解释：
 * - 下标 0 ：另两个 10 在下标 2 和 3 ，|0 - 2| + |0 - 3| = 5
 * - 下标 1 ：只有这一个 5 在数组中，所以到相同元素的间隔之和是 0
 * - 下标 2 ：另两个 10 在下标 0 和 3 ，|2 - 0| + |2 - 3| = 3
 * - 下标 3 ：另两个 10 在下标 0 和 2 ，|3 - 0| + |3 - 2| = 4
 * <p>
 * 提示：
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= 10^5
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no5965_intervals_between_identical_elements {
    /**
     * 超时了
     *
     * @param arr
     * @return
     */
    public long[] getDistances1(int[] arr) {
        int len = arr.length;
        long[] result = new long[len];

        Map<Integer, List> posMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int val = arr[i];
            List<Integer> posList = posMap.getOrDefault(val, new ArrayList());
            posList.add(i);

            posMap.put(val, posList);
        }

        for (int i = 0; i < len; i++) {
            int val = arr[i];
            long res = calDistanceSum(posMap.get(val), i);
            result[i] = res;
        }
        return result;
    }

    private long calDistanceSum(List<Integer> posList, int i) {
        long res = 0;
        for (int iter : posList) {
            if (iter >= i) {
                res = res + iter - i;
            } else {
                res = res + i - iter;
            }
        }
        return res;
    }

    /**
     * 执行用时：798 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：103.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public long[] getDistances(int[] arr) {
        int len = arr.length;
        long[] result = new long[len];

        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> indexMap = new HashMap<>();
        Map<Integer, BigInteger> preSumMap = new HashMap<>();
        Map<Integer, BigInteger> suffSumMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int val = arr[i];
            Integer count = countMap.getOrDefault(val, 0);
            count++;
            countMap.put(val, count);

            BigInteger suffSum = suffSumMap.getOrDefault(val, new BigInteger("0"));
            suffSum = suffSum.add(new BigInteger(i+""));
            suffSumMap.put(val, suffSum);
        }

        for (int i = 0; i < len; i++) {
            int val = arr[i];
            int index = indexMap.getOrDefault(val, 0);
            int count = countMap.get(val);

            BigInteger suffixSum = suffSumMap.get(val);
            BigInteger prefixSum = preSumMap.getOrDefault(val, new BigInteger("0"));

            //BigInteger sum = new BigInteger(suffixSum+"");
            BigInteger sum1 = new BigInteger((count - index)+"");
            sum1 = sum1.multiply(new BigInteger(i+""));

            BigInteger sum = suffixSum.subtract(sum1);

            sum1 = new BigInteger( index+"");
            sum1 = sum1.multiply(new BigInteger(i+""));
            sum = sum.add(sum1);

            sum = sum.subtract(prefixSum);

            result[i] = sum.longValue();//suffixSum - i * (count - index) + i * index - prefixSum;

            suffixSum = suffixSum.subtract(new BigInteger(i+""));
            suffSumMap.put(val, suffixSum);

            prefixSum = prefixSum.add(new BigInteger(i+""));
            preSumMap.put(val, prefixSum);

            indexMap.put(val, index + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        no5965_intervals_between_identical_elements obj = new no5965_intervals_between_identical_elements();
        int[] input = new int[]{2, 1, 3, 1, 2, 3, 3};
        long[] result = obj.getDistances(input);
        System.out.println("result=" + JSON.toJSONString(result) + ",input=" + JSON.toJSONString(input));
        //输入：arr = [2,1,3,1,2,3,3]
        //输出：[4,2,7,2,4,4,5]

        input = new int[]{10, 5, 10, 10};
        result = obj.getDistances(input);
        System.out.println("result=" + JSON.toJSONString(result) + ",input=" + JSON.toJSONString(input));
        //输入：arr = [10,5,10,10]
        //输出：[5,0,3,4]

        input = new int[]{1, 5, 10, 11};
        result = obj.getDistances(input);
        System.out.println("result=" + JSON.toJSONString(result) + ",input=" + JSON.toJSONString(input));
    }
}
