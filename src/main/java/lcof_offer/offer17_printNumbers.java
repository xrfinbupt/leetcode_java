package lcof_offer;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * <p>
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * <p>
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 * <p>
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 * <p>
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 */
public class offer17_printNumbers {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.49%的用户
     * 内存消耗：46.1 MB, 在所有 Java 提交中击败了90.05%的用户
     */
    public int[] printNumbers(int n) {
        int count = 1;
        while (n-- > 0) {
            count *= 10;
        }
        //System.out.println(count);

        int[] resArray = new int[count - 1];
        for (int i = 1; i < count; i++) {
            resArray[i - 1] = i;
        }
        return resArray;
    }
}
