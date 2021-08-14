package lcof;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * <p>
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * <p>
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 */
public class offer11_minArray {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了22.72%的用户
     */
    public int minArray(int[] numbers) {
        int len = numbers.length;
        if (numbers[0] < numbers[len - 1]) return numbers[0];

        for (int i = 1; i < len; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[len - 1];
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了73.24%的用户
     */
    public int minArray2(int[] numbers) {
        int len = numbers.length;
        if (numbers[0] < numbers[len - 1]) return numbers[0];

        int leftVal = numbers[0], j = 0, k = len - 1;
        while (j < len && numbers[j] == leftVal) j++;
        while (k >= 0 && numbers[k] == leftVal) k--;

        if (j == len || k == 0) return numbers[0];

        int l = j, r = k;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (numbers[mid] >= numbers[0]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return numbers[0] < numbers[l] ? numbers[0] : numbers[l];
    }

    public static void main(String[] args) {
        offer11_minArray obj = new offer11_minArray();
        int res = obj.minArray2(new int[]{3, 3, 3, 0, 0, 1});
        System.out.println(res);

        res = obj.minArray2(new int[]{3, 3, 3, 3, 3, 3});
        System.out.println(res);

        res = obj.minArray2(new int[]{10, 1, 10, 10, 10});
        System.out.println(res);

        res = obj.minArray2(new int[]{10, 10, 10, 1, 10, 10, 10});
        System.out.println(res);
    }
}
