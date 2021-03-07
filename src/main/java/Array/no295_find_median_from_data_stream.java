package Array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 */
public class no295_find_median_from_data_stream {
    public static void main(String args[]){
        MedianFinder obj = new MedianFinder();
        for(int i = 1;i<=10;i++) {
            obj.addNum3(i);
            System.out.println("add i:"+i+" mid:" + obj.findMedian3());
        }
        System.out.println();
        MedianFinder obj2 = new MedianFinder();
        for(int i = 10;i>0;i--) {
            obj2.addNum3(i);
            System.out.println("add i:"+i+" mid:" + obj2.findMedian3());
        }

        System.out.println();
        MedianFinder obj3 = new MedianFinder();
        int nums[] = new int[]{-40,-12,-16,-14};
        for(int i = 0;i<nums.length;i++) {
            obj3.addNum3(nums[i]);
            System.out.println("add i:"+nums[i]+" mid:" + obj3.findMedian3());
        }
        System.out.println();
        MedianFinder obj4 = new MedianFinder();
        nums = new int[]{40,12,16,14};
        for(int i = 0;i<nums.length;i++) {
            obj4.addNum3(nums[i]);
            System.out.println("add i:"+nums[i]+" mid:" + obj4.findMedian3());
        }
    }
}

class MedianFinder {
    //如果我们可以用以下方式维护两个堆：
    //1 用于存储输入数字中较小一半的最大堆
    //2 用于存储输入数字的较大一半的最小堆
    // peek 最小值(从小到大 堆排序)
    private PriorityQueue<Integer> pMinUp = null;
    // peek 最大值(从大到小 堆排序)
    private PriorityQueue<Integer> pMaxDown = null;
    private int size = 0;
    /** initialize your data structure here. */
    public MedianFinder() {
        pMinUp = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        pMaxDown = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    /**
     * 我感觉 逻辑不是很清晰 可以简化
     * @param num
     */

    public void addNum(int num) {
        size++;
        int shouldSize =  (1+size)/2 ;
        int leftSize = size - shouldSize;

        int midUp = Integer.MIN_VALUE;
        if(pMinUp.size()>0) midUp = pMinUp.peek();
        int midDown = Integer.MIN_VALUE;
        if(pMaxDown.size()>0) midDown = pMaxDown.peek();

        if ((num >= midUp) || (pMaxDown.size() > 0) && (num > midDown)) {
            if (pMinUp.size() < shouldSize) {
                pMinUp.offer(num);
            } else if (pMaxDown.size() < leftSize) {
                if(num > midUp){
                    pMinUp.poll();
                    pMinUp.offer(num);

                    pMaxDown.offer(midUp);
                }else {
                    pMaxDown.offer(num);
                }
            } else if (pMinUp.size() == shouldSize) {
                pMinUp.poll();
                pMinUp.offer(num);

                pMaxDown.offer(midUp);
            }
        } else {
            if (pMinUp.size() < shouldSize) {
                pMinUp.offer(midDown);

                pMaxDown.poll();
                pMaxDown.offer(num);
            } else if (pMinUp.size() == shouldSize) {
                if(num > midUp){
                    pMinUp.poll();
                    pMinUp.offer(num);

                    pMaxDown.offer(midUp);
                }else {
                    pMaxDown.offer(num);
                }
            }
        }
    }

    /**
     * 这种方法 思路清晰些 时间 慢点点
     * @param num
     */
    public void addNum2(int num) {
        size++;
        int shouldSize =  (1+size)/2 ;
        int leftSize = size - shouldSize;

        int midUp = Integer.MIN_VALUE;
        if(pMinUp.size()>0) midUp = pMinUp.peek();
        int midDown = Integer.MIN_VALUE;
        if(pMaxDown.size()>0) midDown = pMaxDown.peek();

        // 上半区间
        if (num > midUp) {
            if (pMinUp.size() < shouldSize) {
                pMinUp.offer(num);
            } else if (pMinUp.size() == shouldSize) {
                pMinUp.poll();
                pMinUp.offer(num);

                pMaxDown.offer(midUp);
            }
        }
        // 中部区间
        else if((pMaxDown.size() > 0) && (num >= midDown)){
            if (pMinUp.size() < shouldSize) {
                pMinUp.offer(num);
            } else if (pMinUp.size() == shouldSize) {
                pMaxDown.offer(num);
            }
        }
        // 下半区间
        else {
            if (pMinUp.size() < shouldSize) {
                pMinUp.offer(midDown);

                pMaxDown.poll();
                pMaxDown.offer(num);
            } else if (pMinUp.size() == shouldSize) {
                pMaxDown.offer(num);
            }
        }
    }

    public double findMedian() {
        if(size%2 == 0){
            int p1 = pMinUp.peek();
            int p2 = pMaxDown.peek();
            return (p1+p2)/2.0;
        }else{
            int result = pMinUp.peek();
            return (double)result;
        }
    }

    /**
     * 参考 很简洁 思想太妙了
     * https://leetcode-cn.com/problems/find-median-from-data-stream/solution/you-xian-dui-lie-python-dai-ma-java-dai-ma-by-liwe/
     *
     * @param num
     */
    public void addNum3(int num) {
        size += 1;
        pMaxDown.offer(num);
        pMinUp.add(pMaxDown.poll());
        // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
        if ((size & 1) != 0) {
            pMaxDown.add(pMinUp.poll());
        }
    }

    public double findMedian3() {
        if ((size & 1) == 0) {
            // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
            return (double) (pMaxDown.peek() + pMinUp.peek()) / 2;
        } else {
            // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
            return (double) pMaxDown.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */