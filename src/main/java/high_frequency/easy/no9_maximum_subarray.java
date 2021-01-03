package high_frequency.easy;

// https://github.com/azl397985856/leetcode/blob/master/problems/53.maximum-sum-subarray-cn.md
// https://leetcode-cn.com/problems/maximum-subarray/
// 简要的说明
// 能用几种方法解答

public class no9_maximum_subarray {

    // 方法1 暴力法+剪枝法
    // 时间复杂度 o(n^2)
    public int maxSubArray_method1(int[] nums) {
        if(nums==null || nums.length==0) return Integer.MIN_VALUE;

        int max = Integer.MIN_VALUE;
        int preSum = 0;
        int len = nums.length;
        for(int i=0;i<len;i++){
            preSum = 0;
            for(int j=i;j<len;j++){
                preSum += nums[j];
                max = Math.max(max, preSum);
            }
        }
        return max;
    }

    // 方法2 合理利用规则 max= preSum(j) - preSum(i) i为-1到j-1之间使preSum为最小值
    // 时间复杂度 o(n)
    public int maxSubArray_method2(int[] nums) {
        if(nums==null || nums.length==0) return Integer.MIN_VALUE;

        int max = nums[0];
        int min = 0;
        int preSum = 0;
        int len = nums.length;
        int preArray[] = new int[len];
        int minArray[] = new int[len];

        for(int i=0;i<len;i++){
            preSum += nums[i];
            preArray[i] = preSum;
            min = Math.min(min, preSum);
            minArray[i] = min;

            if(i>0) {
                max = Math.max(max, preArray[i] - minArray[i-1]);
            }
        }
        return max;
    }

    // 方法3 动态规划
    // 首先要计算出
    //      初始值 currMax = nums[0]  max = currMax
    //      递推公式 currMax = Math.max(currMax + nums[i],nums[i])
    public int maxSubArray_method3(int[] nums) {
        if(nums==null || nums.length==0) return Integer.MIN_VALUE;

        int max = nums[0];
        int currMax = nums[0];
        int len = nums.length;

        for(int i=1;i<len;i++){
            currMax = Math.max(currMax + nums[i],nums[i]);
            max = Math.max(max,currMax);
        }
        return max;
    }

    // 方法4 分治法
    public int maxSubArray_method4(int[] nums) {
        if(nums==null || nums.length==0) return Integer.MIN_VALUE;

        return helper(nums,0,nums.length-1);
    }

    private int helper(int[] nums, int l, int r) {
        if (l > r) return Integer.MIN_VALUE;

        int mid = (l + r) >>> 1;
        int leftMax = helper(nums, l, mid - 1);
        int rightMax = helper(nums, mid + 1, r);

        int leftMaxSum = 0;
        int leftSum = 0;
        // left surfix maxSum start from index mid - 1 to l
        for (int i = mid - 1; i >= l; i--) {
            leftSum += nums[i];
            leftMaxSum = Math.max(leftMaxSum, leftSum);
        }

        int rightMaxSum = 0;
        int rightSum = 0;
        // right prefix maxSum start from index mid + 1 to r
        for (int i = mid + 1; i <= r; i++) {
            rightSum += nums[i];
            rightMaxSum = Math.max(rightSum, rightMaxSum);
        }

        // max(left, right, crossSum)
        return Math.max(leftMaxSum + rightMaxSum + nums[mid], Math.max(leftMax, rightMax));
    }
}
