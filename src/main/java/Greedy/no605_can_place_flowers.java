package Greedy;

/**
 * 605. Can Place Flowers
 * <p>
 * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 * <p>
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 * <p>
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 *  
 * Constraints:
 * 1 <= flowerbed.length <= 2 * 10^4
 * flowerbed[i] is 0 or 1.
 * There are no two adjacent flowers in flowerbed.
 * 0 <= n <= flowerbed.length
 * <p>
 * 链接：https://leetcode-cn.com/problems/can-place-flowers
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no605_can_place_flowers {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.81%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了62.50%的用户
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;
        int len = flowerbed.length;

        for (int i = 0; i < len; i++) {
            if (flowerbed[i] > 0) continue;

            int pre = i > 0 ? flowerbed[i - 1] : 0;
            int next = i < len-1 ? flowerbed[i + 1] : 0;
            if(pre==0 && next==0){
                n--;
                flowerbed[i] = 1;
            }
            if(n<=0) break;
        }

        if (n == 0) return true;
        return false;
    }
}
