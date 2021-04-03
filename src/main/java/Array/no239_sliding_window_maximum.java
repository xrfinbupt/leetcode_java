package Array;

import java.util.*;

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
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int len = nums.length;
        int resultLen = len - k + 1;
        int[] result = new int[resultLen];
        int temp[] = new int[k];//当作 环形数组
        int start = 0;
        int index = 0;

        // 最大值 出现次数
        int lastMax = nums[0],lastMaxCount = 0;
        int currPop = nums[0];// 当前pop out值
        // 计算最大值 最大值次数
        for (int i = 0; i < k; i++) {
            if (nums[i] > lastMax) lastMax = nums[i];
            temp[i] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            if (temp[i] == lastMax) lastMaxCount++;
        }

        result[index++] = lastMax;
        for (int i = 1; i < resultLen; i++) {
            currPop = temp[start];
            int curr = nums[k + i - 1];
            temp[start++] = curr;

            if(curr == lastMax) lastMaxCount ++;
            else if (curr > lastMax) {
                lastMax = curr;
                lastMaxCount = 1;
            } else {
                if(lastMax == currPop) lastMaxCount --;

                // 重新计算最大值 最大值次数
                if (currPop == lastMax && lastMaxCount ==0) {
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

    /**
     * 方法2 优先队列（堆）时间复杂度O(nlogn)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] ans = new int[nums.length - k +1];
        int index = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0]? o2[0] - o1[0]:o2[1] - o1[1];
            }
        });
        for(int i=0;i<k;i++){
            pq.offer(new int[]{nums[i],i});
        }
        ans[index++] = pq.peek()[0];
        for(int i=k;i<nums.length;i++){
            pq.offer(new int[]{nums[i],i});
            while(pq.peek()[1] <= i - k){
                pq.poll();
            }
            ans[index++] = pq.peek()[0];
        }

        return ans;
    }

    /**
     * 方法3：双端队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] ans = new int[nums.length - k +1];
        int index = 0;
        Deque<Integer> queue = new LinkedList<>();
        for(int i=0;i<k;i++){
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()])
                queue.pollLast();
            queue.offerLast(i);
        }

        ans[index++] = nums[queue.peekFirst()];
        for(int i=k;i<nums.length;i++){
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()])
                queue.pollLast();

            queue.offerLast(i);
            while(i-k >= queue.peekFirst())
                queue.pollFirst();

            ans[index++] = nums[queue.peekFirst()];
        }

        return ans;
    }

    /**
     * 方法4：和双端队列类似 只不过用数组替换双端队列 思想都是一样
     * 执行用时：16 ms, 在所有 Java 提交中击败了 93.46% 的用户
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int temp[] = new int[k];
        int ans[] = new int[nums.length - k + 1];
        int start = 0, end = 0, size = 1;
        temp[0] = 0;
        for (int i = 1; i < k; i++) {
            while (size > 0 && nums[i] > nums[temp[end]]) {
                end--;
                size--;
            }
            temp[++end] = i;
            size++;
        }
        int index = 0;
        ans[index++] = nums[temp[start]];

        for (int i = k; i < nums.length; i++) {
            while (size > 0 && nums[i] > nums[temp[end]]) {
                end--;
                size--;
                if (end < 0) end += k;
            }
            if (size == 0) {
                start = 0;
                end = -1;
            }

            if (k == 1 || size < k) {
                end += 1;
                end = end % k;
                temp[end] = i;
                size++;
            } else if (size == k) {
                start++;
                start = start % k;
                end += 1;
                end = end % k;
                temp[end] = i;
            }

            while (size > 0 && i - k >= temp[start]) {
                start++;
                size--;
                start = start % k;
            }
            ans[index++] = nums[temp[start]];
        }
        return ans;
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

        data = new int[]{-9361,-750,-8435,-5590,-5835,2958,-3942,-8209,-8241};
        result = obj.maxSlidingWindow(data,3);
        System.out.print("array(-9361,-750,-8435,-5590,-5835,2958,-3942,-8209,-8241) k=3:");
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");
        for (int i = 0; i < 1000; i++) {
            List<Integer> input = new ArrayList<>();
            for (int j = 100; j < 110; j++) {
                input.add((int) Math.random() * j);
            }
            int ans[] = input.stream().mapToInt(Integer::intValue).toArray();
            obj.maxSlidingWindow(ans,Math.max((int)Math.random()*10,3));
            System.out.println(i);
        }
    }
}
