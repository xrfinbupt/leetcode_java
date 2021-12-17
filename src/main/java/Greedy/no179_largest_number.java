package Greedy;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. 最大数
 * <p>
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * <p>
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出："1"
 * <p>
 * 示例 4：
 * 输入：nums = [10]
 * 输出："10"
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 * <p>
 * 链接：https://leetcode-cn.com/problems/largest-number
 *
 * @author xurongfei
 * @Date 2021/12/17
 */
public class no179_largest_number {
    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了35.52%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了14.86%的用户
     */
    public String largestNumber(int[] nums) {
        String[] sorted = new String[nums.length];
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            sorted[i] = nums[i] + "";
            if (nums[i] == 0) {
                zeroCount++;
            }
        }
        if (zeroCount == nums.length) {
            return "0";
        }
        Arrays.sort(sorted, new myComparator());

        StringBuilder sb = new StringBuilder();
        for (String iter : sorted) {
            sb.append(iter);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        no179_largest_number obj = new no179_largest_number();
        String input = obj.largestNumber(new int[]{10, 2});
        System.out.println(input);

        input = obj.largestNumber(new int[]{20, 2});
        System.out.println(input);

        input = obj.largestNumber(new int[]{32, 2});
        System.out.println(input);

        input = obj.largestNumber(new int[]{2, 32});
        System.out.println(input);

        input = obj.largestNumber(new int[]{42, 43});
        System.out.println(input);

        input = obj.largestNumber(new int[]{42, 431});
        System.out.println(input);
        input = obj.largestNumber(new int[]{431, 42});
        System.out.println(input);
        input = obj.largestNumber(new int[]{34323, 3432});
        System.out.println(input);
    }

    class myComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String input1 = o1 + o2;
            String input2 = o2 + o1;
            return input2.compareTo(input1);
        }
    }
}
