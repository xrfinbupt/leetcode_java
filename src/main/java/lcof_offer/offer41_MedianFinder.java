package lcof_offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xurongfei
 * @Date 2021/9/5
 * <p>
 * 剑指 Offer 41. 数据流中的中位数
 * <p>
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * <p>
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * <p>
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *  
 * 限制：
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 * 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 */
public class offer41_MedianFinder {
    private PriorityQueue<Integer> upQueue;
    private PriorityQueue<Integer> downQueue;

    /**
     * 执行用时：76 ms, 在所有 Java 提交中击败了50.69%的用户
     * 内存消耗：49.7 MB, 在所有 Java 提交中击败了40.78%的用户
     */
    /**
     * initialize your data structure here.
     */
    public offer41_MedianFinder() {
        upQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        downQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        if (upQueue.size() == downQueue.size()) {
            upQueue.add(num);
            downQueue.add(upQueue.poll());
        } else {
            upQueue.add(num);
            if (upQueue.peek() < downQueue.peek()) {
                downQueue.add(upQueue.poll());
                upQueue.add(downQueue.poll());
            }
        }
    }

    public double findMedian() {
        if (upQueue.size() == downQueue.size()) {
            return (upQueue.peek() + downQueue.peek()) / 2.0;
        }
        return downQueue.peek();
    }

    public static void main(String[] args) {
        offer41_MedianFinder obj = new offer41_MedianFinder();
        obj.addNum(3);
        obj.addNum(2);
        System.out.println(obj.findMedian());

        obj.addNum(1);
        System.out.println(obj.findMedian());
    }
}

