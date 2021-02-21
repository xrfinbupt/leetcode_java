package Array;

import java.util.Arrays;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]

 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]

 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 * 链接：https://leetcode-cn.com/problems/insert-interval
 */
public class no57_insert_interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) return new int[][]{{newInterval[0], newInterval[1]}};
        int len = intervals.length;
        if (newInterval[0] < intervals[0][0] && newInterval[0] > intervals[len - 1][1]) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }

        int max = intervals[len - 1][1];
        if (newInterval[1] > max) max = newInterval[1];

        int[] array = new int[max + 1];
        Arrays.fill(array, -1);
        for (int[] iter : intervals) {
            array[iter[0]] = iter[1];
        }

        if (array[newInterval[0]] == -1) {
            array[newInterval[0]] = newInterval[1];
        } else {
            array[newInterval[0]] = Math.max(newInterval[1], array[newInterval[0]]);
        }

        // 处理区间重叠问题
        int count = array.length;
        int num = 0;
        for (int i = 0; i < count; i++) {
            int val = array[i];
            if (val != -1) num++;
            else continue;

            int lastPos = i;
            int maxVal = val;
            while (val > lastPos) {
                for (int j = lastPos + 1; j <= val; j++) {
                    if (array[j] != -1) {
                        maxVal = Math.max(array[i], array[j]);
                        array[j] = -1;
                    }
                }
                lastPos = val;
                if (maxVal > array[i]) {
                    val = maxVal;
                    array[i] = maxVal;
                }
            }
        }

        int[][] result = new int[num][2];
        int index = 0;
        for (int i = 0; i < count; i++) {
            int val = array[i];
            if (val == -1) continue;
            result[index][0] = i;
            result[index++][1] = val;
        }
        return result;
    }
    public static void main(String args[]) {
        no57_insert_interval obj = new no57_insert_interval();
        int data[][] = new int[][]{{1,3},{6,9}};
        int[][] result = obj.insert(data,new int[]{2,5});
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");

        data = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        result = obj.insert(data,new int[]{4,8});
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");

        data = new int[][]{{1, 2}, {2, 4}, {3, 10}, {10, 18},{18,110}};
        result = obj.insert(null,new int[]{4,8});
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");

        data = new int[][]{{1, 2}, {2, 4}, {4, 10}, {10, 18},{18,110}};
        result = obj.insert(data,new int[]{0,1});
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");
    }
}
