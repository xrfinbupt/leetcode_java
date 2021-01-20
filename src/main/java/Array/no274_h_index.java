package Array;

import java.util.Arrays;

/**
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * <p>
 * 示例：
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 *  
 * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 * <p>
 * https://leetcode-cn.com/problems/h-index
 */
public class no274_h_index {

    /**
     * 我自己想到的方法 没想到一次提交就通过率 自我打气一下
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;

        Arrays.sort(citations);
        int len = citations.length;
        int index = 0;
        int pre = 0;
        for (int i = len; i > 0; i--) {
            index++;
            int val = citations[i - 1];
            if (val >= index) {
                pre = val;
            } else {
                return Math.min(pre, index - 1);
            }
        }

        return Math.min(pre, index);
    }

    public static void main(String args[]) {
        no274_h_index obj = new no274_h_index();
        int result = obj.hIndex(new int[]{3, 0, 6, 1, 5});
        System.out.println("array(3,0,6,1,5):" + result);

        result = obj.hIndex(new int[]{3, 6});
        System.out.println("array(3,6):" + result);

        result = obj.hIndex(new int[]{3});
        System.out.println("array(3):" + result);
    }
}
