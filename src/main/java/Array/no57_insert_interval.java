package Array;

import java.util.Arrays;

/**
 * 57. 插入区间
 *
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
 * 提示：
 *
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^5
 *
 * 链接：https://leetcode-cn.com/problems/insert-interval
 */
public class no57_insert_interval {
    /**
     * 时间复杂度O(n) 空间复杂度O(n)
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) return new int[][]{{newInterval[0], newInterval[1]}};
        int len = intervals.length;
        if (newInterval[0] <= intervals[0][0] && newInterval[0] >= intervals[len - 1][1]) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }

        int max = intervals[len - 1][1];
        if (newInterval[1] > max) max = newInterval[1];

        // 利用数组的有序性 达到排序的目的
        int[] array = new int[max + 1];
        Arrays.fill(array, -1);

        // step1 insert array
        for (int[] iter : intervals) {
            array[iter[0]] = iter[1];
        }

        boolean flag = false;
        if (array[newInterval[0]] == -1) {
            array[newInterval[0]] = newInterval[1];
        } else {
            array[newInterval[0]] = Math.max(newInterval[1], array[newInterval[0]]);
            flag = true;
        }

        // step2 处理区间重叠问题
        int count = array.length;
        int num = 0;
        for (int i = newInterval[0]; i < count; i++) {
            int val = array[i];
            if (val != -1) num++;
            else continue;

            int lastPos = i;
            int maxVal = val;
            while (val > lastPos) {
                for (int j = lastPos + 1; j <= val; j++) {
                    if (array[j] != -1) {
                        maxVal = Math.max(maxVal, array[j]);
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

        // step3 输出
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

    /**
     * 可以利用原数组 降低空间复杂度(主要是需要new一个数组返回 实际效果打折了)
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        int len = intervals.length;
        if (newInterval[0] <= intervals[0][0] && newInterval[1] >= intervals[len - 1][1]) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }else if(newInterval[1] < intervals[0][0]){
            int [][]array = new int[len+1][2];
            array[0][0] = newInterval[0];
            array[0][1] = newInterval[1];
            for(int i=0;i<len;i++){
                array[i+1][0] = intervals[i][0];
                array[i+1][1] = intervals[i][1];
            }
            return array;
        }else if(newInterval[0]> intervals[len-1][1]){
            int [][]array = new int[len+1][2];
            for(int i=0;i<len;i++){
                array[i][0] = intervals[i][0];
                array[i][1] = intervals[i][1];
            }
            array[len][0] = newInterval[0];
            array[len][1] = newInterval[1];
            return array;
        }else{
            int num = len + 1;
            for(int i=0;i<len;i++){
                int iter[] = intervals[i];
                int pre[] = i>0?intervals[i-1]:null;

                // 1 小于iter[0]
                if(newInterval[0] < iter[0]){
                    // 1.1 和前区间重叠
                    if(pre!=null && pre[1] >= newInterval[0]){
                        pre[1] = Math.max(pre[1],newInterval[1]);
                        num--;
                        return calculateResult(intervals,i-1,num);
                    }else{
                        // 1.2 和当前区间重叠
                        if(newInterval[1] >= iter[0]){
                            iter[0] = newInterval[0];
                            iter[1] = Math.max(iter[1],newInterval[1]);
                            num--;
                            return calculateResult(intervals,i,num);
                        }else{
                            // 1.3 独立的区间
                            return calculateResult2(intervals,newInterval,i,num);
                        }
                    }
                }// 2 等于iter[0]
                else if(newInterval[0] == iter[0]){
                    iter[1] = Math.max(iter[1],newInterval[1]);
                    num--;
                    return calculateResult(intervals,i,num);
                }
            }
            intervals[len-1][1] = Math.max(intervals[len-1][1],newInterval[1]);

            return intervals;
        }
    }
    public int[][] calculateResult2(int[][] intervals, int[] newInterval,int pos,int targetLen) {
        int [][]ans = new int[targetLen][2];
        int i=0;
        for(int[] iter:intervals){
            if(i==pos){
                ans[i][0] = newInterval[0];
                ans[i][1] = newInterval[1];
                i++;
            }
            ans[i][0] = iter[0];
            ans[i][1] = iter[1];

            i++;
        }
        return ans;
    }
    public int[][] calculateResult(int[][] intervals, int pos,int targetLen) {
        int len = intervals.length;
        int []pre = intervals[pos];
        for(int i=pos+1;i<len;i++){
            int []iter = intervals[i];
            if(pre[1]>=iter[0]){
                pre[1] = Math.max(pre[1],iter[1]);
                iter[0] = -1;
                targetLen --;
            }else{
                break;
            }
        }
        int [][]ans = new int[targetLen][2];
        int i=0;
        for(int[] iter:intervals){
            if(iter[0]==-1) continue;
            ans[i][0] = iter[0];
            ans[i][1] = iter[1];
            i++;
        }
        return ans;
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
        result = obj.insert(data,new int[]{4,8});
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");

        data = new int[][]{{1, 2}, {2, 4}, {4, 10}, {10, 18},{18,110}};
        result = obj.insert(data,new int[]{0,0});
        for (int[] iter : result) {
            System.out.println(iter[0] + ":" + iter[1]);
        }
        System.out.println("-----------");
    }
}
