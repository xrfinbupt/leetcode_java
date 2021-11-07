package Other;

/**
 * 461. 汉明距离
 * <p>
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * <p>
 * 示例 1：
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * 示例 2：
 * 输入：x = 3, y = 1
 * 输出：1
 * <p>
 * 提示：
 * 0 <= x, y <= 2^31 - 1
 * <p>
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 *
 * @author xurongfei
 * @Date 2021/11/7
 */
public class no461_hamming_distance {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了5.87%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了22.82%的用户
     */
    public int hammingDistance(int x, int y) {
        Long target = 1L;
        int result = 0;
        while (true) {
            boolean first = (x & target) > 0 ? true : false;
            boolean second = (y & target) > 0 ? true : false;
            if (check(first, second)) {
                result++;
            }
            target = target << 1;
            if (target > x && target > y) {
                break;
            }
        }

        return result;
    }

    private boolean check(boolean first, boolean second) {
        if (first != second) {
            return true;
        }
        return false;
    }
}
