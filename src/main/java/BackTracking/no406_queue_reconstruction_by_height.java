package BackTracking;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 406. 根据身高重建队列
 * <p>
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * 示例 1：
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * <p>
 * 示例 2：
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 *  
 * <p>
 * 提示：
 * 1 <= people.length <= 2000
 * 0 <= hi <= 10^6
 * 0 <= ki < people.length
 * 题目数据确保队列可以被重建
 * <p>
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 *
 * @author xurongfei
 * @Date 2021/11/21
 */
public class no406_queue_reconstruction_by_height {
    /**
     * 执行用时：642 ms, 在所有 Java 提交中击败了5.17%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了91.93%的用户
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue1(int[][] people) {
        List<int[]> datas = new ArrayList<>();
        for (int[] iter : people) {
            datas.add(iter);
        }
        Collections.sort(datas, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return 0;
            }
        });
        int len = datas.size();
        int[][] result = new int[len][2];

        //System.out.println("after sort:" + JSON.toJSONString(datas));
        for (int i = 0; i < len; i++) {
            result[i] = datas.get(i);
            rank(result, i);
        }
        return result;
    }

    /**
     * 执行用时：17 ms, 在所有 Java 提交中击败了13.36%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了46.13%的用户
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else if (o1[1] != o2[1]) {
                    return o2[1] - o1[1];
                }
                return 0;
            }
        });
        int len = people.length;
        int[][] result = new int[len][];

        for (int i = 0; i < len; i++) {
            int[] iter = people[i];
            int temp = iter[1];
            int index = -1;
            for (int j = 0; j < len; j++) {
                if (result[j] == null) {
                    temp--;
                }
                if (temp < 0) {
                    index = j;
                    break;
                }
            }
            result[index] = iter;
        }
        return result;
    }

    private void rank(int[][] result, int i) {
        if (i == 0) return;
        for (int j = i; j >= 0; j--) {
            int count = 0;
            int val = result[j][0];
            int val_count = result[j][1];
            for (int k = j - 1; k >= 0; k--) {
                if (result[k][0] >= val) {
                    count++;
                }
            }
            if (count == val_count) {
                return;
            } else {
                if (j > 0) {
                    int[] pre = result[j - 1];
                    if (pre[0] > val) {
                        swap(result, j - 1, j);
                    }
                }
            }
        }
    }

    private void swap(int[][] result, int i, int j) {
        int[] temp = result[i];
        result[i] = result[j];
        result[j] = temp;
    }

    public static void main(String[] args) {
        no406_queue_reconstruction_by_height obj = new no406_queue_reconstruction_by_height();
        int[][] input = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println("input:" + JSON.toJSONString(input));
        int[][] result = obj.reconstructQueue(input);
        System.out.println("result:" + JSON.toJSONString(result));
        System.out.println();

        input = new int[][]{{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}};
        System.out.println("input:" + JSON.toJSONString(input));
        result = obj.reconstructQueue(input);
        System.out.println("result:" + JSON.toJSONString(result));
    }
}
