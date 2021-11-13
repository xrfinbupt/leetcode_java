package Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 提示：
 * 1 <= k <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 *
 * @author xurongfei
 * @Date 2021/11/13
 */
public class no215_kth_largest_element_in_an_array {

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了58.82%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了64.25%的用户
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int mid = (len + 1) / 2;
        if (k > mid) {
            Arrays.sort(nums);
            return nums[len-k];
        } else {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int iter : nums) {
                if (queue.size() >= k) {
                    if (iter > queue.peek()) {
                        queue.remove();
                        queue.add(iter);
                    }
                } else {
                    queue.add(iter);
                }
            }
            return queue.peek();
        }
    }

    public static void main(String[] args) {
        no215_kth_largest_element_in_an_array obj = new no215_kth_largest_element_in_an_array();
        int k = 2;
        int[] datas = new int[]{3, 2, 1, 5, 6, 4};
        int res = obj.findKthLargest(datas, k);
        System.out.println("k=" + k + "," + res);

        k = 3;
        datas = new int[]{3, 2, 1, 5, 6, 4};
        res = obj.findKthLargest(datas, k);
        System.out.println("k=" + k + "," + res);

        k = 4;
        datas = new int[]{3, 2, 1, 5, 6, 4};
        res = obj.findKthLargest(datas, k);
        System.out.println("k=" + k + "," + res);

        k = 5;
        datas = new int[]{3, 2, 1, 5, 6, 4};
        res = obj.findKthLargest(datas, k);
        System.out.println("k=" + k + "," + res);
    }
}
