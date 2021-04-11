package lcci;

/**
 * 面试题 08.03. 魔术索引
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 *
 * 示例1:
 *  输入：nums = [0, 2, 3, 4, 5]
 *  输出：0
 *  说明: 0下标的元素为0
 *
 * 示例2:
 *  输入：nums = [1, 1, 1]
 *  输出：1
 *
 * 说明:
 * nums长度在[1, 1000000]之间
 * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
 *
 * 在原书里此题是有两个小问的，它们的描述很相似。本题即为第二个小问的描述，而第一个小问保证了题目给定的数组是「严格单调递增」的，就是不会有重复的数字。
 * 因此第一个小问是经典的二分查找问题，时间复杂度为O(logn)。而第二个小问相当于是第一个小问的进阶：面试官在看到你解决了问题之后，会问你如果数组中可以出现重复的元素应该怎么做，就变成了本题。
 *
 * 链接：https://leetcode-cn.com/problems/magic-index-lcci/solution/mo-zhu-suo-yin-by-leetcode-solution/
 *
 */
public class magic_index_lcci {
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) return i;
        }
        return -1;
    }
}
