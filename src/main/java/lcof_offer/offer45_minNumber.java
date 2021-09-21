package lcof_offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xurongfei
 * @Date 2021/9/21
 * <p>
 * 剑指 Offer 45. 把数组排成最小的数
 * <p>
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * <p>
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 * <p>
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 * <p>
 * 提示:
 * 0 < nums.length <= 100
 * 说明:
 * <p>
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 * <p>
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 */
public class offer45_minNumber {
    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了23.69%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了44.35%的用户
     */
    public String minNumber(int[] nums) {
        if (nums.length == 0) {
            return "";
        }
        if (nums.length < 2) {
            return nums[0] + "";
        }
        List<String> sortList = new ArrayList<String>();
        for (int iter : nums) sortList.add(iter + "");
        Collections.sort(sortList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               String new1 = o1+o2;
               String new2 = o2+o1;
               return new1.compareTo(new2);
            }
        });

        StringBuilder result = new StringBuilder();
        for (String iter : sortList) result.append(iter);
        return result.toString();
    }

    public static void main(String[] args) {
        offer45_minNumber obj = new offer45_minNumber();
        System.out.println(obj.minNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(obj.minNumber(new int[]{10, 2}));
        System.out.println(obj.minNumber(new int[]{30, 3}));
        System.out.println(obj.minNumber(new int[]{34, 3}));
        System.out.println(obj.minNumber(new int[]{3, 30, 34, 5, 9, 91, 98}));
        System.out.println(obj.minNumber(new int[]{12, 121}));
    }
}
