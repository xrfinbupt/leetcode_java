package Array;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *  
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * <p>
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 *
 * @author xurongfei
 * @Date 2021/11/19
 */
public class no347_topk_frequent_elements {
    /**
     * 执行用时：12 ms, 在所有 Java 提交中击败了90.29%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了29.13%的用户
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int iter : nums) {
            int count = countMap.getOrDefault(iter, 0);
            countMap.put(iter, count + 1);
        }
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (Map.Entry<Integer, Integer> iter : countMap.entrySet()) {
            if (queue.size() < k) {
                Integer[] currDatas = new Integer[]{iter.getKey(), iter.getValue()};
                queue.add(currDatas);
            } else {
                Integer[] datas = queue.peek();
                if (datas[1] < iter.getValue()) {
                    queue.remove();

                    Integer[] currDatas = new Integer[]{iter.getKey(), iter.getValue()};
                    queue.add(currDatas);
                }
            }
        }
        int len = queue.size();
        for (int i = 0; i < len; i++) {
            result[i] = queue.remove()[0];
        }
        return result;
    }

    public static void main(String[] args) {
        no347_topk_frequent_elements obj = new no347_topk_frequent_elements();
        int[] res = obj.topKFrequent(new int[]{1, 5, 1, 5, 2, 3}, 2);
        System.out.println(JSON.toJSONString(res));
    }
}
