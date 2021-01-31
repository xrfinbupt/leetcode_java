package Array;

import java.util.Arrays;
import java.util.Collections;

/**
 * 给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。找出让数组所有元素相等的最小操作次数。
 *
 * 示例：
 * 输入：
 * [1,2,3]
 * 输出：
 * 3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 */
public class no453_minimum_moves_to_equal_array_elements {
    /**
     * 方法1 我自己想的这种方案超时了 下面几种方法是参考官方答案写的
     * @param nums
     * @return
     */
    public int minMoves_method1(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        Arrays.sort(nums);

        int len = nums.length;
        int count = 0;
        while (true) {
            if (nums[len - 2] > nums[len - 1]) {
                int temp = nums[len - 1];
                nums[len - 1] = nums[len - 2];
                nums[len - 2] = temp;
            } else if (nums[len - 2] <= nums[len - 1]) {
                int i = len - 2;
                for (; i >= 0; i--) {
                    if (nums[i] != nums[len - 1]) break;
                }
                if (i < 0) return count;
                else {
                    Arrays.sort(nums, 0, len - 1);
                }

                count++;
                for (i = 0; i < len - 1; i++) nums[i]++;
            }
        }
    }

    /**
     * 方法2：排序后
     * @param nums
     * @return
     */
    public int minMoves_method2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        Arrays.sort(nums);

        int len = nums.length;
        int moveCount = 0;
        for (int i = 1; i < len; i++) {
            moveCount += (nums[i] - nums[0]);
        }
        return moveCount;
    }
    /**
     * 方法3：不用排序 选择一个最小值即可 就是数学法
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        int min = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            min = Math.min(nums[i], min);
        }
        int moveCount = 0;
        for (int i = 0; i < len; i++) {
            moveCount += (nums[i] - min);
        }
        return moveCount;
    }
    /**
     * 方法4：动态规划 需要先排序
     * @param nums
     * @return
     */
    public int minMoves_method4(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        Arrays.sort(nums);

        int len = nums.length;
        int moveCount = 0;
        for (int i = 1; i < len; i++) {
            int diff = moveCount + nums[i] - nums[i-1];
            nums[i] += moveCount;
            moveCount += diff;
        }
        return moveCount;
    }
    public static void main(String args[]){
        no453_minimum_moves_to_equal_array_elements obj = new no453_minimum_moves_to_equal_array_elements();
        int count = obj.minMoves(new int[]{1,2,3});
        System.out.println("array(1,2,3) result:"+count);
        count = obj.minMoves(new int[]{1,2,2,3});
        System.out.println("array(1,2,2,3) result:"+count);//4
        count = obj.minMoves(new int[]{2,2,2,2});
        System.out.println("array(2,2,2,2) result:"+count);//4
        count = obj.minMoves(new int[]{1,2147483647});
        System.out.println("array(1,2147483647) result:"+count);//4
        count = obj.minMoves(new int[]{1,3});
        System.out.println("array(1,3) result:"+count);//4
    }
}
