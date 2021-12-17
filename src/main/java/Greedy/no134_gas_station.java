package Greedy;

/**
 * 134. 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * <p>
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * <p>
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * <p>
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * Constraints:
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 *
 * <p>
 * 链接：https://leetcode-cn.com/problems/gas-station
 *
 * @author xurongfei
 * @Date 2021/12/12
 */
public class no134_gas_station {
    /**
     * 执行用时：40 ms, 在所有 Java 提交中击败了15.56%的用户
     * 内存消耗：59.1 MB, 在所有 Java 提交中击败了26.59%的用户
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int diff = gas[i] - cost[i];
            max = Math.max(max, diff);
            sum += diff;
        }
        if (sum < 0) {
            return -1;
        }

        for (int i = 0; i < len; i++) {
            int diff = gas[i] - cost[i];
            if (max == 0) {
                if (diff == 0 && check(gas, cost, i)) {
                    return i;
                }
            } else {
                if (diff > 0 && check(gas, cost, i)) {
                    return i;
                }
            }

        }

        return -1;
    }

    private boolean check(int[] gas, int[] cost, int i) {
        int len = gas.length;
        int count = 0;
        int sum = 0;
        while (count < len) {
            int index = i % len;
            sum += (gas[index] - cost[index]);
            if (sum < 0) {
                return false;
            }
            count++;
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        no134_gas_station obj = new no134_gas_station();
        int res = obj.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
        System.out.println(res);

        res = obj.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3});
        System.out.println(res);

        res = obj.canCompleteCircuit(new int[]{2, 2}, new int[]{2, 2});
        System.out.println(res);

        res = obj.canCompleteCircuit(new int[]{2}, new int[]{2});
        System.out.println(res);

        res = obj.canCompleteCircuit(new int[]{5, 8, 2, 8}, new int[]{6, 5, 6, 6});
        System.out.println(res);
    }
}
