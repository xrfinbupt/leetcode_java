package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. 用最少数量的箭引爆气球
 * <p>
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * <p>
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 * <p>
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * <p>
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * <p>
 * 示例 4：
 * 输入：points = [[1,2]]
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 *  
 * 提示：
 * 1 <= points.length <= 10^4
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no452_minimum_number_of_arrows_to_burst_balloons {
    /**
     * 执行用时：57 ms, 在所有 Java 提交中击败了43.77%的用户
     * 内存消耗：69.3 MB, 在所有 Java 提交中击败了33.85%的用户
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        int len = points.length, result = 1;
        int s = points[0][0], e = points[0][1];
        for (int i = 1; i < len; i++) {
            int[] curr = points[i];
            if (intersection(s,e,curr[0],curr[1])) {
                s = Math.max(s,curr[0]);
                e = Math.min(e,curr[1]);
            }else{
                result++;
                s = curr[0];
                e = curr[1];
            }
        }
        return result;
    }
    private boolean intersection(int s,int e,int start,int end){
        if(s<=start && start <= e){
            return true;
        }
        if(s<=end && end <= e){
            return true;
        }
        return false;
    }
    public static void main(String args[]){
        no452_minimum_number_of_arrows_to_burst_balloons obj = new no452_minimum_number_of_arrows_to_burst_balloons();
        int[][] points = new int[][]{{10,16},{2,8},{1,6},{7,12}};
        int res = obj.findMinArrowShots(points);
        System.out.println("res="+res);//2

        points = new int[][]{{1,2},{3,4},{5,6},{7,8}};
        res = obj.findMinArrowShots(points);
        System.out.println("res="+res);//4

        points = new int[][]{{1,2},{2,3},{3,4},{4,5}};
        res = obj.findMinArrowShots(points);
        System.out.println("res="+res);//2

        points = new int[][]{{1,2}};
        res = obj.findMinArrowShots(points);
        System.out.println("res="+res);//1

        points = new int[][]{{2,3},{2,3},{3,4}};
        res = obj.findMinArrowShots(points);
        System.out.println("res="+res);//1
    }
}
