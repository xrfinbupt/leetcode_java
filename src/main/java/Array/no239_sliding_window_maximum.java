package Array;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7

 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 *
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 *
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *  
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class no239_sliding_window_maximum {
    /**
     * 这种方法 超时了 后来优化了一版 勉强通过
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int resultLen = len - k + 1;
        int[] result = new int[resultLen];
        int temp[] = new int[k];
        int start = 0;
        int index = 0;

        int lastMax = nums[0],lastMaxCount = 0;
        int lastPop = nums[0];
        for (int i = 0; i < k; i++) {
            if (nums[i] > lastMax) lastMax = nums[i];
            temp[i] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            if (temp[i] == lastMax) lastMaxCount++;
        }

        result[index++] = lastMax;

        for (int i = 1; i < resultLen; i++) {
            lastPop = temp[start];
            temp[start++] = nums[k + i - 1];
            if(nums[k + i - 1] == 79){
                System.out.println(79);
            }
            if(nums[k + i -1] == lastMax) lastMaxCount ++;
            else if (nums[k + i - 1] > lastMax) {
                lastMax = nums[k + i - 1];
                lastMaxCount = 1;
            } else {
                if(lastMax == lastPop) lastMaxCount --;

                if (lastPop == lastMax && lastMaxCount ==0) {
                    lastMax = temp[0];
                    for (int j = 1; j < k; j++) {
                        if (temp[j] > lastMax) lastMax = temp[j];
                    }

                    for (int j = 0; j < k; j++) {
                        if (temp[j] == lastMax) lastMaxCount++;
                    }
                }
            }

            result[index++] = lastMax;
            start = start % k;
        }
        return result;
    }
    public static void main(String args[]) {
        no239_sliding_window_maximum obj = new no239_sliding_window_maximum();
        int data[] = new int[]{1,3,-1,-3,5,3,6,7};
        int[] result = obj.maxSlidingWindow(data,3);
        System.out.print("array(1,3,-1,-3,5,3,6,7) k=3:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{1};
        result = obj.maxSlidingWindow(data,1);
        System.out.print("array(1) k=1:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{1,-1};
        result = obj.maxSlidingWindow(data,1);
        System.out.print("array(1,-1) k=1:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{9,11};
        result = obj.maxSlidingWindow(data,2);
        System.out.print("array(9,11) k=2:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{4,-2,3};
        result = obj.maxSlidingWindow(data,2);
        System.out.print("array(4,-2,3) k=2:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{1,2,3,4,5,6,7,8,9};
        result = obj.maxSlidingWindow(data,3);
        System.out.print("array(1,2,3,4,5,6,7,8,9) k=3:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{9,8,7,6,5,4,13,2,1};
        result = obj.maxSlidingWindow(data,3);
        System.out.print("array(9,8,7,6,5,4,13,2,1) k=3:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{9,10,9,-7,-4,-8,2,-6};
        result = obj.maxSlidingWindow(data,5);
        System.out.print("array(9,10,9,-7,-4,-8,2,-6) k=5:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");

        data = new int[]{-95,92,-85,59,-59,-14,88,-39,2,92,94,79,78,-58,37,48,63,-91,91,74,-28};
        result = obj.maxSlidingWindow(data,10);
        System.out.print("array(-95,92,-85,59,-59,-14,88,-39,2,92,94,79,78,-58,37,48,63,-91,91,74,-28,39,90) k=10:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");
    }
}
