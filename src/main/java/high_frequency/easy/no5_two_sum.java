package high_frequency.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/two-sum/
public class no5_two_sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length;
        for(int i=0;i<len;i++){

            Integer preValue = map.get(target - nums[i]);
            if(preValue==null) {
                map.put(nums[i],i);
                continue;
            } else{
                if(preValue == i) continue;
                return new int[]{preValue,i};
            }
        }
        return null;
    }
}
