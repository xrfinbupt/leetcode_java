package DP;

/**
 * 739. 每日温度
 * <p>
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 * <p>
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 *
 * @author xurongfei
 * @Date 2021/12/10
 */
public class no739_daily_temperatures {
    /**
     * 执行用时：321 ms, 在所有 Java 提交中击败了18.97%的用户
     * 内存消耗：48.5 MB, 在所有 Java 提交中击败了49.70%的用户
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] max = new int[len];

        int maxVal = 0;
        for (int i = len - 1; i >= 0; i--) {
            maxVal = Math.max(maxVal, temperatures[i]);
            max[i] = maxVal;
        }

        for (int i = 0; i < len; i++) {
            if (temperatures[i] >= max[i]) {
                max[i] = 0;
            } else {
                int count = 0;
                for (int j = i + 1; j < len; j++) {
                    count++;
                    if (temperatures[i] < temperatures[j]) {
                        max[i] = count;
                        break;
                    }
                }
            }
        }

        return max;
    }
}
