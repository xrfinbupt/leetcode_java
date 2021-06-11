package lcci;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * 面试题 17.05.  字母与数字
 *
 * 给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。
 *
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 *
 * 示例 1:
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 *
 * 示例 2:
 * 输入: ["A","A"]
 * 输出: []
 *
 * 提示：
 * array.length <= 100000
 *
 * 链接：https://leetcode-cn.com/problems/find-longest-subarray-lcci
 *
 */
public class find_longest_subarray_lcci {
    private boolean isNumber(char ch) {
        if (ch >= '0' && ch <= '9') return true;
        else return false;
    }

    /**
     * 超时了
     * @param array
     * @return
     */
    public String[] findLongestSubarray(String[] array) {
        if (array == null || array.length < 2) return new String[]{};
        int len = array.length;

        int result = 0;
        int l = 0, r = 0;

        int num = 0, chNum = 0;
        boolean numFlagArray[] = new boolean[len];
        for(int i=0;i<len;i++){
            String val = array[i];
            boolean isNumber = isNumber(val.charAt(0));
            if(isNumber) numFlagArray[i] = true;

            if (isNumber) num ++;
            else chNum ++;
        }
        if (chNum == num) return array;

        for (int i = 0; i < len-1; i++) {
            if (result>0 && result >= len - i) break;

            num = 0;
            chNum = 0;
            for(int j=i;j<len;j++) {
                if (numFlagArray[j]) num++;
                else chNum++;

                if (num == chNum && j- i + 1 > result) {
                    result = j-i + 1;
                    l = i;
                    r = j;
                }
            }
            if (result>0 && result == len - i) break;

        }
        if (result == len) return array;
        if (result == 0) return new String[]{};

        String[] arrayResult = new String[result];
        for (int i = l; i <= r; i++) {
            arrayResult[i - l] = array[i];
        }
        return arrayResult;
    }

    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了75.68%的用户
     * 内存消耗：54 MB, 在所有 Java 提交中击败了67.57%的用户
     *
     * @param array
     * @return
     */
    public String[] findLongestSubarray2(String[] array) {
        if (array == null || array.length < 2) return new String[]{};
        int len = array.length;

        int result = 0;
        int l = 0, r = 0;

        int num = 0, chNum = 0;
        HashMap<Integer, Integer> diff2PosMap = new HashMap<>();
        boolean numFlagArray[] = new boolean[len];

        for (int i = 0; i < len; i++) {
            String val = array[i];
            boolean isNumber = isNumber(val.charAt(0));
            numFlagArray[i] = isNumber;

            if (isNumber) num++;
            else chNum++;

            int diff = num - chNum;
            if (diff == 0) {
                if (i + 1 > result) {
                    result = i + 1;
                    l = 0;
                    r = i;
                }
                continue;
            }

            Integer pos = diff2PosMap.get(diff);
            if (pos == null) {
                diff2PosMap.put(diff, i);
            }  else {
                if (numFlagArray[pos] == isNumber) {
                    if (i - pos > result) {
                        result = i - pos;
                        l = pos;
                        r = i - 1;
                    }
                } else {
                    if (i - pos > result) {
                        result = i - pos;
                        l = pos + 1;
                        r = i;
                    }
                }
            }
        }
        if (result == len) return array;
        if (result == 0) return new String[]{};

        String[] arrayResult = new String[result];
        for (int i = l; i <= r; i++) {
            arrayResult[i - l] = array[i];
        }
        return arrayResult;
    }

    public static void main(String args[]){

        String array [] = new String[]{"w","G","j","b","R","34","P","38","s","G","44","i","22","71","T","p","95","42","h","41","72","j","72","99","u","88","D","P","26","0","78","T","u","76","99","45","71","65","0","15","S","80","x","41","72","o","l","H","2","k","36","67","b","J","10","a","x","2","32","S","n","41","x","16","X","37","8","38","K","i","75","y","Y","16","F","59","23","97","n","50","9"};
        find_longest_subarray_lcci obj = new find_longest_subarray_lcci();
        String result[] = obj.findLongestSubarray2(array);
        System.out.println(JSON.toJSONString(result));
    }
}
