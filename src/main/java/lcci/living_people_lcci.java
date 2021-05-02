package lcci;

/**
 * 面试题 16.10. 生存人数
 *
 * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
 *
 * 你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。如果一个人在某一年的任意时期处于生存状态，那么他应该被纳入那一年的统计中。例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
 *
 * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
 *
 * 示例：
 * 输入：
 * birth = {1900, 1901, 1950}
 * death = {1948, 1951, 2000}
 * 输出： 1901
 *  
 * 提示：
 * 0 < birth.length == death.length <= 10000
 * birth[i] <= death[i]
 *
 * 链接：https://leetcode-cn.com/problems/living-people-lcci
 */
public class living_people_lcci {
    public int maxAliveYear(int[] birth, int[] death) {
        int min = 1900,max=2000;
        int len = birth.length;
        for(int i=0;i<len;i++){
            if(birth[i]<min) min = birth[i];
            if(death[i]>max) max = death[i];
        }
        int result = 0;
        int targetYear = min;
        for(int i=min;i<=max;i++){
            int count = 0;
            for(int j=0;j<len;j++){
                if(birth[j]<= i && i<= death[j]) count++;
            }
            if(count>result) {
                result = count;
                targetYear = i;
            }
        }
        return targetYear;
    }

    /**
     * 方法2 前缀和
     * https://leetcode-cn.com/problems/living-people-lcci/solution/javaqian-zhui-he-by-cjvv/
     *
     * @param birth
     * @param death
     * @return
     */
    public int maxAliveYear2(int[] birth, int[] death) {
        int[] lives = new int[102];
        int len = birth.length;
        for (int i = 0; i < len; i++) {
            lives[birth[i] - 1900]++;
        }
        for (int i = 0; i < len; i++) {
            lives[death[i] - 1900 + 1]--;
        }
        int[] preSum = new int[102];

        preSum[0] = lives[0];
        int minYear = 1900;
        int max = preSum[0];
        for (int i = 1; i < 102; i++) {
            preSum[i] = lives[i] + preSum[i - 1];
            if (preSum[i] > max) {
                max = preSum[i];
                minYear = i + 1900;
            }
        }
        return minYear;
    }
    public int maxAliveYear3(int[] birth, int[] death) {
        int[] lives = new int[102];
        int len = birth.length;
        for (int i = 0; i < len; i++) {
            lives[birth[i] - 1900]++;
        }
        for (int i = 0; i < len; i++) {
            lives[death[i] - 1900 + 1]--;
        }
        int preSum  = lives[0];
        int minYear = 1900;
        int max = preSum;
        for (int i = 1; i < 102; i++) {
            preSum = lives[i] + preSum;
            if (preSum > max) {
                max = preSum;
                minYear = i + 1900;
            }
        }
        return minYear;
    }
}
