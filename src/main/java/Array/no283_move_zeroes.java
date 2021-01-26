package Array;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * https://leetcode-cn.com/problems/move-zeroes
 *
 */
public class no283_move_zeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int len = nums.length;
        int l = 0, r = 0;
        while (l < len && r < len && l <= r) {
            while (l < len && nums[l] != 0) {
                l++;
            }
            if (l == len) return;

            if (r < l) r = l;
            while (r < len && nums[r] == 0) {
                r++;
            }
            if (r == len) return;

            while (l < r && r < len && nums[l] == 0 && nums[r] != 0) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r++;
            }
        }
    }
    public static void main(String args[]){
        no283_move_zeroes obj = new no283_move_zeroes();
        obj.moveZeroes(new int[]{0, 1, 0, 3, 12});
        System.out.println();
        obj.moveZeroes(new int[]{2, 1, 1, 3, 12});
        System.out.println();
        obj.moveZeroes(new int[]{0, 0, 0});
        System.out.println();
        obj.moveZeroes(new int[]{2, 1, 1, 0, 0});
        System.out.println();
    }
}
