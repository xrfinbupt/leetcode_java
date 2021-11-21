package Stack_Queue;

import java.util.LinkedList;

/**
 * 155. 最小栈
 *
 * https://leetcode-cn.com/problems/min-stack/
 * https://github.com/azl397985856/leetcode/blob/master/problems/155.min-stack.md
 */
public class no155_min_stack {
    private LinkedList<Integer> queue;
    private LinkedList<Integer> minQueue;

    /**
     * initialize your data structure here.
     */
    public no155_min_stack() {
        queue = new LinkedList<>();
        minQueue = new LinkedList<>();
    }

    public void push(int x) {
        queue.addLast(x);
        int min = getMin();
        if (x < min) {
            minQueue.addLast(x);
        } else {
            minQueue.addLast(min);
        }
    }

    public void pop() {
        queue.pollLast();
        minQueue.pollLast();
    }

    public int top() {
        return queue.peekLast();
        //return queue.peekFirst();
    }

    public int getMin() {
        if (minQueue.isEmpty()) return Integer.MAX_VALUE;
        Integer result = minQueue.getLast();
        return result;
    }

    public static void main(String args[]) {
        no155_min_stack obj = new no155_min_stack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        int v1 = obj.getMin();
        obj.pop();
        int v2 = obj.top();
        int v3 = obj.getMin();
        System.out.println("end");
    }
}

/**
 * sequence 6   4   2  -3
 * stack    0  -2  -2  -5
 * min      6   4   2  -3
 * <p>
 * sequence -2  0  -3
 * stack    0   2  -1
 * min     -2  -2  -3
 * 方法2 优化存储空间 (这种思维方式 很奇妙,有个坑：int边界)
 *
 */
class no21_min_stack_method2 {
    private LinkedList<Long> queue;
    private long min;

    /**
     * initialize your data structure here.
     */
    public no21_min_stack_method2() {
        queue = new LinkedList<>();
        min = 0;
    }

    public void push(int x) {
        if (queue.isEmpty()) {
            queue.addLast(0L);
            min = x;
        } else {
            long diff = (long)x - (long)min;
            queue.addLast(diff);
            if (diff < 0) {
                min = x;
            }
        }
    }

    /**
     * diff < 0 说明当前栈顶的元素就是pop值
     */
    public void pop() {
        Long diff = queue.pollLast();
        if (diff != null && diff < 0) {
            min = min - diff;
        }
    }

    public int top() {
        Long diff = queue.peekLast();
        if (diff < 0) return (int)min;
        else return (int)(min + diff);
    }

    public int getMin() {
        return (int)min;
    }
    public static void main(String args[]) {
        no21_min_stack_method2 obj = new no21_min_stack_method2();
 //["MinStack","push","push","push",  "top","pop","getMin"   ,"pop","getMin","pop",   "push","top","getMin",   "push","top","getMin",    "pop","getMin"]
//[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]

        obj.push(2147483646);
        obj.push(2147483646);
        obj.push(2147483647);

        int v2 = obj.top();
        obj.pop();
        int v3 = obj.getMin();

        obj.pop();
        int v1 = obj.getMin();
        obj.pop();

        obj.push(2147483647);
        v2 = obj.top();
         v1 = obj.getMin();

        obj.push(-2147483648);
        v2 = obj.top();
        v1 = obj.getMin(); //???  2147483647  ==> -2147483648

        obj.pop();
        v1 = obj.getMin();
        System.out.println("end");
    }
}
