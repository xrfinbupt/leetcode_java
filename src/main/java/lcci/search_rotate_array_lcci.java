package lcci;

/**
 * 面试题 10.03. 搜索旋转数组
 *
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 *
 * 示例1:
 *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 *  输出: 8（元素5在该数组中的索引）
 *
 * 示例2:
 *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 *  输出：-1 （没有找到）
 *
 * 提示:
 * arr 长度范围在[1, 1000000]之间
 *
 * 链接：https://leetcode-cn.com/problems/search-rotate-array-lcci
 * 相似题：leetcode 154、81、34
 */
public class search_rotate_array_lcci {
    /**
     * 参考网上解答
     * 先找到旋转数组最小值的位置，这样根据最小值将数组分为左右两个区间（左区间一定大于等于arr[0]，右区间一定小于arr[0]）
     * 判断target在哪个区间中（根据arr[0]可以判断）
     * 用二分搜索在对应区间搜索target
     *
     * @param arr
     * @param target
     * @return
     */
    public int search(int[] arr, int target) {
        int len = arr.length;
        int left = 0, right = len - 1;
        while (0 < right && arr[left] == arr[right]) right--;
        if (arr[left] > arr[right]) {
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] >= arr[0]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        if (left == right) {
            if (target < arr[0]) {
                right = len - 1;
            } else {
                left = 0;
                right--;
            }
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                right = mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return arr[right] == target ? right : -1;
    }
}
