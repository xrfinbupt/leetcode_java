package Array;

import java.util.Arrays;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class no56_merge_intervals {
    /**
     * 排序？数组下标
     * 很有成就感的一次刷题，断断续续想了2天左右
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int len = 0;
        for (int[] iter : intervals) {
            len = Math.max(len,iter[1]);
        }
        len = len + 1;

        int array[] = new int[len];
        Arrays.setAll(array, p -> -1);

        // 相当于排序 按照starti排序
        for (int[] iter : intervals) {
            int val = array[iter[0]];
            if (val == 0 || iter[1] > val) {
                array[iter[0]] = iter[1];
            }
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            int val = array[i];
            if (val >= 0) count++;
            else continue;

            int lastPos = i;
            int maxVal = val;
            while (val >= lastPos) {
                for (int j = lastPos + 1; j <= val; j++) {
                    if (array[j] > 0) {
                        maxVal = Math.max(maxVal, array[j]);
                        array[j] = -1;
                    }
                }

                lastPos = val;
                if (maxVal > array[i]) {
                    val = maxVal;
                    array[i] = maxVal;
                }
                if (val == lastPos) break;
            }
        }
        int result[][] = new int[count][2];
        count = 0;
        for (int i = 0; i < len; i++) {
            if (array[i] >= 0) {
                result[count][0] = i;
                result[count][1] = array[i];
                count++;
            }
        }

        return result;
    }

    public static void main(String args[]) {
        no56_merge_intervals obj = new no56_merge_intervals();
        int data[][] = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = obj.merge(data);
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");

        data = new int[][]{{1, 2}, {2, 2}, {10, 10}, {15, 18},{0,0}};
        result = obj.merge(data);
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");

        data = new int[][]{{1, 2}, {2, 4}, {3, 10}, {10, 18},{18,110}};
        result = obj.merge(data);
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");
    }
}
