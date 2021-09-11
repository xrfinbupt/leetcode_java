package lcof_offer;

import java.util.Stack;

/**
 * @author xurongfei
 * @Date 2021/9/11
 * <p>
 * 剑指 Offer 31. 栈的压入、弹出序列
 * <p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
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
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 * 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
 * <p>
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 */
public class offer31_validateStackSequences {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了88.90%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了59.15%的用户
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        if (len == 0) return true;

        Stack<Integer> stack = new Stack<>();
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
        offer31_validateStackSequences obj = new offer31_validateStackSequences();
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 2, 1, 5}));
        System.out.println(obj.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }
}
