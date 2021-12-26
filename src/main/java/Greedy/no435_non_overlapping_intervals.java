package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * <p>
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 注意:
 * <p>
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * <p>
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * <p>
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * <p>
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * <p>
 * Constraints:
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no435_non_overlapping_intervals {
    /**
     * 执行用时：67 ms, 在所有 Java 提交中击败了14.38%的用户
     * 内存消耗：94.5 MB, 在所有 Java 提交中击败了50.27%的用户
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        int len = intervals.length;
        int result = 0;
        int e = intervals[0][1];
        for (int i = 1; i < len; i++) {
            int [] curr = intervals[i];
            if(e>curr[0]){
                result++;
                if(e > curr[1]){
                    e = curr[1];
                }
            }else{
                e = curr[1];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        no435_non_overlapping_intervals obj = new no435_non_overlapping_intervals();
        int[][] intervals = new int[][]{{1,2}, {2,3}, {3,4}, {1,3}};
        int res = obj.eraseOverlapIntervals(intervals);
        System.out.println("res="+res);
        //输入: [ [1,2], [2,3], [3,4], [1,3] ]
        //输出: 1

        intervals = new int[][]{{1,2}, {1,2}, {1,2}};
        res = obj.eraseOverlapIntervals(intervals);
        System.out.println("res="+res);
        //输入: [ [1,2], [1,2], [1,2] ]
        //输出: 2

        intervals = new int[][]{{1,12}};
        res = obj.eraseOverlapIntervals(intervals);
        System.out.println("res="+res);
        //输入: [ [1,12] ]
        //输出: 0

        intervals = new int[][]{{-11,-2},{-12,-3},{-3,4},{-1,3}};
        res = obj.eraseOverlapIntervals(intervals);
        System.out.println("res="+res);
        // 2

        intervals = new int[][]{{1,9},{2,3},{3,4},{7,13}};
        res = obj.eraseOverlapIntervals(intervals);
        System.out.println("res="+res);
        // 1

        intervals = new int[][]{{1,9},{2,3},{3,4},{7,13}};
        res = obj.eraseOverlapIntervals(intervals);
        System.out.println("res="+res);
        // 1
    }
}
