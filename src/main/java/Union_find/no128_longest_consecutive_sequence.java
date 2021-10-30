package Union_find;

import java.util.*;

/**
 * 128. 最长连续序列
 * <p>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 */
public class no128_longest_consecutive_sequence {
    /**
     * 执行用时：27 ms, 在所有 Java 提交中击败了43.40%的用户
     * 内存消耗：56.8 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, UnionFind> map = new HashMap<>();

        for (int iter : nums) {
            UnionFind pre = map.get(iter - 1);
            UnionFind next = map.get(iter + 1);
            UnionFind curr = map.get(iter);
            if (curr == null) {
                curr = new UnionFind(iter);
                if (next != null) {
                    curr.next = next;
                    next.pre = curr;
                }
                if (pre != null) {
                    pre.next = curr;
                    curr.pre = pre;
                }
                map.put(iter, curr);
            }
        }

        Set<Integer> usedSet = new HashSet<>();
        int len = 1;
        for (int iter : nums) {
            if (usedSet.contains(iter)) {
                continue;
            }
            len = Math.max(len, calLen(iter, usedSet, map));
        }

        return len;
    }

    /**
     * 执行用时：23 ms, 在所有 Java 提交中击败了44.46%的用户
     * 内存消耗：49.6 MB, 在所有 Java 提交中击败了77.96%的用户
     */
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, UnionFind> map = new HashMap<>();

        for (int iter : nums) {
            UnionFind pre = map.get(iter - 1);
            UnionFind next = map.get(iter + 1);
            UnionFind curr = map.get(iter);
            if (curr == null) {
                curr = new UnionFind(iter);
                if (next != null) {
                    curr.next = next;
                    next.pre = curr;
                }
                if (pre != null) {
                    pre.next = curr;
                    curr.pre = pre;
                }
                map.put(iter, curr);
            }
        }

        int maxLen = 1;
        for (int iter : nums) {
            int len = 0;
            UnionFind curr = map.get(iter);
            if(curr==null)continue;

            while (curr.pre != null) {
                UnionFind pre = curr.pre;
                curr = pre;
            }

            while (curr != null) {
                len++;
                map.remove(curr.val);
                curr = curr.next;
            }
            maxLen = Math.max(len, maxLen);
        }

        return maxLen;
    }

    private int calLen(int iter, Set<Integer> usedSet, HashMap<Integer, UnionFind> map) {
        int len = 0;
        UnionFind uf = map.get(iter);
        UnionFind pre = uf;
        while (uf.pre != null) {
            pre = uf.pre;
            uf = pre;
        }

        UnionFind curr = pre;
        while (curr != null) {
            usedSet.add(curr.val);
            len++;
            curr = curr.next;
        }

        return len;
    }

    static class UnionFind {
        int val;
        UnionFind pre;
        UnionFind next;

        public UnionFind(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        no128_longest_consecutive_sequence obj = new no128_longest_consecutive_sequence();
        int len = obj.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        System.out.println(len);
        len = obj.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
        System.out.println(len);
        len = obj.longestConsecutive(new int[]{0, 2, 4, 6, 8, 10, 12});
        System.out.println(len);
        len = obj.longestConsecutive(new int[]{});
        System.out.println(len);
    }
}
