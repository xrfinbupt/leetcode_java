package lcof;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 */
public class no9_2stackimpl_queue {

    ArrayDeque<Integer> first = null;
    ArrayDeque<Integer> second = null;

    /**
     * 执行用时：53 ms, 在所有 Java 提交中击败了96.65%的用户
     * 内存消耗：46.5 MB, 在所有 Java 提交中击败了77.33%的用户
     */
    public no9_2stackimpl_queue() {
        first = new ArrayDeque<>();
        second = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        first.addFirst(value);
    }

    public int deleteHead() {
        if(second.size()>0){
            return second.removeFirst();
        }else if(first.size()>0){
            while(first.size()>0){
                second.addFirst(first.removeFirst());
            }
            return second.removeFirst();
        }else{
            return -1;
        }
    }
}
