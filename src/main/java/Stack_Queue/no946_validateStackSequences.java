package Stack_Queue;

import java.util.Stack;

/**
 * @author xurongfei
 * @Date 2021/9/11
 * <p>
 * 946. 验证栈序列
 * <p>
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * <p>
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 * 提示：
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素 互不相同
 * popped.length == pushed.length
 * popped 是 pushed 的一个排列
 * <p>
 * 链接：https://leetcode-cn.com/problems/validate-stack-sequences
 */
public class no946_validateStackSequences {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了86.30%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了40.63%的用户
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        if (len < 1) return true;
        Stack<Integer> stack = new Stack<Integer>();

        int i = 0, j = 0;
        while (i < len || j < len) {
            if (stack.isEmpty()) {
                if (i >= len) break;
                stack.add(pushed[i++]);
            } else {
                if (stack.peek() == popped[j]) {
                    stack.pop();
                    j++;
                } else {
                    if (stack.peek() == popped[j]) {
                        continue;
                    }
                    if (i >= len) break;
                    stack.add(pushed[i++]);
                }
            }
        }
        if (i == j && j == len) return true;
        else return false;
    }

    public static void main(String[] args) {
        no946_validateStackSequences obj = new no946_validateStackSequences();
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 2, 1, 5}));
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }
}
