package lcci;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 16.15. 珠玑妙算
 *
 * 珠玑妙算游戏（the game of master mind）的玩法如下。
 *
 * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
 *
 * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 *
 * 示例：
 * 输入： solution="RGBY",guess="GGRR"
 * 输出： [1,1]
 * 解释： 猜中1次，伪猜中1次。
 *
 * 提示：
 * len(solution) = len(guess) = 4
 * solution和guess仅包含"R","G","B","Y"这4种字符
 *
 * 链接：https://leetcode-cn.com/problems/master-mind-lcci
 *
 */
public class master_mind_lcci {

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param solution
     * @param guess
     * @return
     */
    public int[] masterMind(String solution, String guess) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('R', 0);
        map.put('G', 0);
        map.put('B', 0);
        map.put('Y', 0);

        int matchCount = 0, missCount = 0;
        for (int i = 0; i < 4; i++) {
            char ch = solution.charAt(i);
            char ch2 = guess.charAt(i);
            if (ch == ch2) {
                matchCount++;
            } else {
                Integer result = map.get(ch);
                if (result < 0) {
                    missCount++;
                }
                map.put(ch, result + 1);

                result = map.get(ch2);
                if (result > 0) {
                    missCount++;
                }
                map.put(ch2,result - 1);
            }
        }

        return new int[]{matchCount,missCount};
    }
}
