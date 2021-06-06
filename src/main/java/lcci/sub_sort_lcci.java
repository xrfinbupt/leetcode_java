package lcci;

import com.alibaba.fastjson.JSON;

/**
 * 面试题 16.16. 部分排序
 *
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
 * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * 示例：
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 * 提示：
 * 0 <= len(array) <= 1000000
 *
 * 链接：https://leetcode-cn.com/problems/sub-sort-lcci
 */
public class sub_sort_lcci {

    /**
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.77%的用户
     * 内存消耗：61.4 MB, 在所有 Java 提交中击败了98.19%的用户
     *
     * @param array
     * @return
     */
    public int[] subSort(int[] array) {
        if (array == null || array.length <= 1) return new int[]{-1, -1};

        int len = array.length;
        int leftPos = -1, rightPos = -1;
        for (int i = 0; i < len - 1; i++) {
            if (array[i] > array[i + 1]) {
                leftPos = i;
                break;
            }
        }
        if (leftPos == -1) return new int[]{-1, -1};
        for (int i = len - 1; i > leftPos; i--) {
            if (array[i] < array[i - 1]) {
                rightPos = i;
                break;
            }
        }

        int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        for(int i=leftPos;i<=rightPos;i++){
            max = Math.max(max,array[i]);
            min = Math.min(min,array[i]);
        }
        while(leftPos>0 && array[leftPos-1]>min){
            leftPos--;
        }
        while(rightPos <len-1 && max > array[rightPos+1]){
            rightPos++;
        }

        return new int[]{leftPos, rightPos};
    }
    public static void main(String args[]){
        sub_sort_lcci obj = new sub_sort_lcci();
        System.out.println("1,2,4,7,10,11,7,12,6,7,16,18,19="+JSON.toJSONString(obj.subSort(new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19})));
        System.out.println("="+JSON.toJSONString(obj.subSort(new int[]{})));
        System.out.println("1="+JSON.toJSONString(obj.subSort(new int[]{1})));
        System.out.println("1,2="+JSON.toJSONString(obj.subSort(new int[]{1,2})));
        System.out.println("2,1="+JSON.toJSONString(obj.subSort(new int[]{2,1})));
        System.out.println("1,2,3="+JSON.toJSONString(obj.subSort(new int[]{1,2,3})));
        System.out.println("1,3,2="+JSON.toJSONString(obj.subSort(new int[]{1,3,2})));
        // [1 2] [2 1] [1 2 3] [1 3 2]
    }
}
