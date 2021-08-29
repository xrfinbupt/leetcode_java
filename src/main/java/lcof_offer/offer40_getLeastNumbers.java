package lcof_offer;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xurongfei
 * @Date 2021/8/28
 * <p>
 * 剑指 Offer 40. 最小的k个数
 * <p>
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * <p>
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * <p>
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 */
public class offer40_getLeastNumbers {
    /**
     * 执行用时：17 ms, 在所有 Java 提交中击败了29.97%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了40.92%的用户
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || k < 1) return new int[]{};

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int iter : arr) {
            if (queue.size() >= k) {
                if (queue.peek() > iter) {
                    queue.add(iter);
                    queue.poll();
                }
            } else {
                queue.add(iter);
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll();
        }
        return result;
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.15%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了86.88%的用户
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || k < 1) return new int[]{};
        int len = arr.length;
        int[] temp = new int[10000+1];

        for (int i = 0; i < len; i++) {
            int val = arr[i];
            temp[val] = temp[val]+1;
        }

        int[] result = new int[k];
        int index = 0;
        for (int i = 0; i <= len; i++) {
            while (temp[i] > 0) {
                result[index++] = i;
                temp[i]--;
                if (index == k) {
                    return result;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        offer40_getLeastNumbers obj = new offer40_getLeastNumbers();
        int[] data = obj.getLeastNumbers2(new int[]{1, 2, 3, 4}, 4);
        System.out.println(JSON.toJSONString(data));

        data = obj.getLeastNumbers2(new int[]{1, 3, 4, 2}, 2);
        System.out.println(JSON.toJSONString(data));

        data = obj.getLeastNumbers2(new int[]{1, 2, 3, 0}, 2);
        System.out.println(JSON.toJSONString(data));

        data = obj.getLeastNumbers2(new int[]{1, 0, 3, 2}, 4);
        System.out.println(JSON.toJSONString(data));

        data = obj.getLeastNumbers2(new int[]{1, 2, 3, 0}, 4);
        System.out.println(JSON.toJSONString(data));

        data = obj.getLeastNumbers2(new int[]{1, 0, 3, 0}, 4);
        System.out.println(JSON.toJSONString(data));
    }
}
