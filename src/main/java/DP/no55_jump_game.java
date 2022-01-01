package DP;

/**
 * 55. 跳跃游戏
 * <p>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/jump-game
 */
public class no55_jump_game {
    private int[] path;

    /**
     * 这种方法超时了
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int len = nums.length;

        return subCanJump(nums, len - 1);
    }

    public boolean subCanJump(int nums[], int pos) {
        if (pos == 0) return true;

        for (int j = pos - 1; j >= 0; j--) {
            int wid = pos - j;
            boolean flag = false;
            flag = (nums[j] >= wid) && subCanJump(nums, j);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump2(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int len = nums.length;
        path = new int[len];

        return subCanJump2(nums, len - 1);
    }

    public boolean subCanJump2(int nums[], int pos) {
        if (pos == 0) return true;

        for (int j = pos - 1; j >= 0; j--) {
            int wid = pos - j;

            if (nums[j] < wid) continue;

            boolean flag = path[pos] > 0;
            if (flag) return flag;
            else {
                flag = subCanJump2(nums, j);
                if (flag) {
                    path[pos] = 1;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了83.93%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了97.31%的用户
     * <p>
     * 这种方法 效率高些
     *
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int len = nums.length;
        path = new int[len];
        path[0] = 1;
        int lastPath = 0;
        for (int j = 0; j < len; j++) {
            if (lastPath < j) return false;
            int maxPath = nums[j] + j;

            if (maxPath >= len - 1) return true;
            else if (lastPath >= maxPath) continue;
            else {
                for (int i = lastPath + 1; i <= maxPath; i++) {
                    path[i] = 1;
                }
                lastPath = maxPath;
            }
        }

        return path[len - 1] > 0 ? true : false;
    }

    /**
     * 动态规划思想
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了97.35%的用户
     *
     * @param nums
     * @return
     */
    public boolean canJump4(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int len = nums.length;
        int maxPath = nums[0];
        for (int j = 1; j < len; j++) {
            if (maxPath < j) return false;

            maxPath = Math.max(maxPath, nums[j] + j);
            if (maxPath >= len - 1) return true;
        }

        return maxPath == len - 1 ? true : false;
    }

    public static void main(String[] args) {
        no55_jump_game obj = new no55_jump_game();
        boolean flag = false;
        flag = obj.canJump4(new int[]{2, 0});
        System.out.println(flag);

        flag = obj.canJump4(new int[]{2, 0, 0});
        System.out.println(flag);

        flag = obj.canJump4(new int[]{2, 0, 1, 0});
        System.out.println(flag);

        flag = obj.canJump4(new int[]{2, 3, 1, 1, 4});
        System.out.println(flag);
    }
}
