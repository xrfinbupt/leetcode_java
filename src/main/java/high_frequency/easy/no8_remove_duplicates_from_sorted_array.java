package high_frequency.easy;

// https://github.com/azl397985856/leetcode/blob/master/problems/26.remove-duplicates-from-sorted-array.md

public class no8_remove_duplicates_from_sorted_array {

    /**
     * 方法1 常规思路 比较耗时
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int orgLen = nums.length;
        int delNum = 0;
        int pre = nums[0];
        int rl = -1;
        for (int i = 1; i < orgLen - delNum; ) {
            if (nums[i] == pre) {
                if (rl == -1) rl = i;
            } else {
                // del
                if (rl != -1) {
                    //重新调整i
                    int offset = i - rl;
                    for (int ii = i; ii < orgLen - delNum; ii++) {
                        nums[ii - offset] = nums[ii];
                    }
                    delNum += (i - rl);

                    i = i - offset;
                }
                pre = nums[i];
                rl = -1;
            }
            i++;
        }
        if (rl != -1) {
            //重新调整i
            int offset = orgLen - delNum - rl;
            delNum += offset;
        }
        return orgLen - delNum;
    }

    /**
     * 方法2 双指针方法 思路很精妙
     * <p>
     * 使用快慢指针来记录遍历的坐标。
     * 开始时这两个指针都指向第一个数字
     * 如果两个指针指的数字相同，则快指针向前走一步
     * 如果不同，则两个指针都向前走一步
     * 当快指针走完整个数组后，慢指针当前的坐标加 1 就是数组中不同数字的个数
     */
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int pslow = 0;
        int pfast = 0;
        int orgLen = nums.length;
        while (pfast < orgLen) {
            if (nums[pslow] == nums[pfast]) {
                pfast++;
            } else {
                pslow++;
                nums[pslow] = nums[pfast];
                pfast++;
            }
        }
        return pslow + 1;
    }

    public static void main(String args[]) {
        no8_remove_duplicates_from_sorted_array obj = new no8_remove_duplicates_from_sorted_array();
        int[] nums = new int[]{1, 1, 1, 1, 1, 2, 2, 2, 3};
        int len = obj.removeDuplicates2(nums);
        System.out.println(len);
    }
}
