package lcof_offer;

import java.util.Stack;

/**
 * @author xurongfei
 * @Date 2021/9/4
 * <p>
 * 剑指 Offer 30. 包含min函数的栈
 * <p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * <p>
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 * <p>
 * 提示：
 * 各函数的调用总次数不超过 20000 次
 *  
 * 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
 * <p>
 * 链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
 */
public class offer30_MinStack {
    /**
     * 执行用时：18 ms, 在所有 Java 提交中击败了68.33%的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了12.47%的用户
     */
    private Stack<Long> stack = new Stack<Long>();
    private long currTop;

    /**
     * initialize your data structure here.
     */
    public offer30_MinStack() {
        stack = new Stack<Long>();
        currTop = 0;
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            currTop = x;
            stack.push(0L);
        } else {
            stack.push(x - currTop);
            if (x < currTop) {
                currTop = x;
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }

        long curr = stack.pop();
        if (curr < 0) {
            currTop = currTop - curr;
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        long top = stack.peek();
        if (top < 0) return (int) currTop;
        else {
            return (int) (top + currTop);
        }
    }

    public int min() {
        return (int) currTop;
    }

    public static void main(String[] args) {
        offer30_MinStack obj = new offer30_MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        obj.min();
        obj.pop();
        obj.top();
        obj.min();
    }
}
