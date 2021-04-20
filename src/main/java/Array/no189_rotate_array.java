package Array;

/**
 * 189. 旋转数组
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 进阶：
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *  
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 *
 * 链接：https://leetcode-cn.com/problems/rotate-array
 *
 */
public class no189_rotate_array {
    /**
     * 常规解法
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (k == 0 || k % len == 0) return;

        k = k % len;
        int array[] = new int[k];
        int right = len - k;
        for (int p = right, i = 0; p <= len - 1; p++, i++) array[i] = nums[p];

        int i = len - 1;
        for (int p = right - 1; p >= 0; p--, i--) nums[i] = nums[p];

        for (int p = k - 1; p >= 0; p--, i--) nums[i] = array[p];
    }

    /**
     * 空间复杂度为 O(1)的解法
     * 参考 https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        if (k == 0 || k % len == 0) return;
        k = k % len;
        int count = gcd(k, len);
        for (int i = 0; i < count; i++) {
            int current = i;
            int preVal = nums[current];
            do {
                int next = (current + k) % len;

                // preVal <=> nums[next]
                int temp = nums[next];
                nums[next] = preVal;
                preVal = temp;

                current = next;
            } while (current != i);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    public void rotate3(int[] nums, int k) {
        int len = nums.length;
        if (k == 0 || k % len == 0) return;
        k = k % len;

        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    public void reverse(int nums[],int start,int end){
        for(;start<end;){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
