package WeeklyContest;

import java.util.*;

/**
 * 5970. 参加会议的最多员工数
 * <p>
 * 一个公司准备组织一场会议，邀请名单上有 n 位员工。公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * <p>
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。每位员工喜欢的员工 不会 是他自己。
 * <p>
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目 。
 * <p>
 * 示例 1：
 * 输入：favorite = [2,2,1,2]
 * 输出：3
 * 解释：
 * 上图展示了公司邀请员工 0，1 和 2 参加会议以及他们在圆桌上的座位。
 * 没办法邀请所有员工参与会议，因为员工 2 没办法同时坐在 0，1 和 3 员工的旁边。
 * 注意，公司也可以邀请员工 1，2 和 3 参加会议。
 * 所以最多参加会议的员工数目为 3 。
 * <p>
 * 示例 2：
 * 输入：favorite = [1,2,0]
 * 输出：3
 * 解释：
 * 每个员工都至少是另一个员工喜欢的员工。所以公司邀请他们所有人参加会议的前提是所有人都参加了会议。
 * 座位安排同图 1 所示：
 * - 员工 0 坐在员工 2 和 1 之间。
 * - 员工 1 坐在员工 0 和 2 之间。
 * - 员工 2 坐在员工 1 和 0 之间。
 * 参与会议的最多员工数目为 3 。
 * <p>
 * 示例 3：
 * 输入：favorite = [3,0,1,4,1]
 * 输出：4
 * 解释：
 * 上图展示了公司可以邀请员工 0，1，3 和 4 参加会议以及他们在圆桌上的座位。
 * 员工 2 无法参加，因为他喜欢的员工 0 旁边的座位已经被占领了。
 * 所以公司只能不邀请员工 2 。
 * 参加会议的最多员工数目为 4 。
 *  
 * <p>
 * 提示：
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-employees-to-be-invited-to-a-meeting
 *
 * @author xurongfei
 * @Date 2022/1/2
 */
public class no5970_maximum_employees_to_be_invited_to_a_meeting {
    HashMap<Integer, Integer> ringCountMap = new HashMap<>();
    HashMap<Integer, Integer> twoRingRelationMap = new HashMap<>();

    HashMap<Integer, Integer> path = new HashMap<>();
    int maxCount = 0;
    int currCount = 0;

    /**
     * 超时了
     * @param favorite
     * @return
     */
    public int maximumInvitations(int[] favorite) {
        ringCountMap.clear();
        twoRingRelationMap.clear();
        path.clear();
        maxCount = 0;

        int len = favorite.length;
        for (int i = 0; i < len; i++) {
            path = new HashMap<>();
            currCount = 0;
            dfs(i, i, favorite);
            maxCount = Math.max(maxCount, currCount);
        }
        int twoRingMaxCount = 0;
        Set<Integer> usedKeys = new HashSet<>();
        for (Map.Entry<Integer, Integer> iter : twoRingRelationMap.entrySet()) {
            int key = iter.getKey();
            int val = iter.getValue();
            if (usedKeys.contains(key) || usedKeys.contains(val)) {
                continue;
            }
            twoRingMaxCount += ringCountMap.get(key) + ringCountMap.get(val) - 2;
            usedKeys.add(key);
            usedKeys.add(val);
        }

        return Math.max(maxCount, twoRingMaxCount);
    }

    private void dfs(int first, int i, int[] favorite) {
        if (twoRingRelationMap.containsKey(i)) {
            currCount = path.size() + 2;
            ringCountMap.put(i, Math.max(currCount, ringCountMap.get(i)));
            return;
        }
        if (ringCountMap.containsKey(i)) {
            int ringCount = ringCountMap.get(i);
            currCount = ringCount;
            return;
        }

        int val = path.getOrDefault(i, 0);
        if (val > 1) {
            for (Map.Entry<Integer, Integer> iter : path.entrySet()) {
                if (iter.getValue() > 1) {
                    currCount++;
                }
            }
            //cache
            List<Integer> twoItems = new ArrayList<>();
            for (Map.Entry<Integer, Integer> iter : path.entrySet()) {
                if (iter.getValue() > 1) {
                    ringCountMap.put(iter.getKey(), currCount);
                    if (currCount == 2) {
                        twoItems.add(iter.getKey());
                    }
                }
            }

            if (currCount == 2) {
                Integer firstInRing = i;
                int orgRingCount = ringCountMap.getOrDefault(firstInRing, 0);
                int max = Math.max(path.size(), orgRingCount);
                currCount = max;
                ringCountMap.put(firstInRing, currCount);

                twoRingRelationMap.put(twoItems.get(0), twoItems.get(1));
                twoRingRelationMap.put(twoItems.get(1), twoItems.get(0));
            }
            return;
        }
        path.put(i, val + 1);
        int next = favorite[i];
        dfs(first, next, favorite);
    }

    public static void main(String[] args) {
        no5970_maximum_employees_to_be_invited_to_a_meeting obj = new no5970_maximum_employees_to_be_invited_to_a_meeting();
        int res = obj.maximumInvitations(new int[]{3, 0, 1, 4, 1});
        System.out.println("expect 4,actual=" + res);

        res = obj.maximumInvitations(new int[]{2, 2, 1, 2});
        System.out.println("expect 3,actual=" + res);

        res = obj.maximumInvitations(new int[]{1, 0, 0, 2, 1, 4, 7, 8, 9, 6, 7, 10, 8});
        System.out.println("expect 6,actual=" + res);

        res = obj.maximumInvitations(new int[]{1, 0, 3, 2, 5, 6, 7, 4, 9, 8, 11, 10, 11, 12, 10});
        System.out.println("expect 11,actual=" + res);
        //[1,0,0,2,1,4,7,8,9,6,7,10,8]
    }
}
