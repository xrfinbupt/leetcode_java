package lcci;

/**
 * 面试题 17.21. 直方图的水量
 *
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
 */
public class volume_of_histogram_lcci {
    /**
     * 之前应该做过这道题
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.94%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了84.10%的用户
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if(len<3) return 0;

        int leftArray[] = new int[len];
        int rightArray[] = new int[len];
        int left = 0;
        int right = 0;
        for(int i=0;i<len;i++){
            leftArray[i] = left;
            left = Math.max(left,height[i]);

            rightArray[len - 1 - i] = right;
            right = Math.max(right,height[len - 1 - i]);
        }

        int sum = 0;
        for(int i=0;i<len;i++){
            int val = height[i];
            left  = leftArray[i];
            right = rightArray[i];
            int min = Math.min(left,right);
            if(val<min) sum += min - val;
        }
        return sum;
    }

    public static void main(String args[]){
        volume_of_histogram_lcci obj = new volume_of_histogram_lcci();
        System.out.println(obj.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
