package lcof;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * <p>
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * <p>
 * 参考以下这颗二叉搜索树：
 * <p>
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * <p>
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 * <p>
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 * <p>
 * 提示：
 * 数组长度 <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 */
public class offer33_verifyPostorder {
    boolean flag = true;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了39.92%的用户
     */
    public boolean verifyPostorder(int[] postorder) {
        flag = true;
        int len = postorder.length;
        if (len <= 1) return flag;

        dfs(postorder, 0, len - 2, postorder[len - 1]);

        return flag;
    }

    private void dfs(int[] postorder, int start, int end, int center) {
        if (end <= start) return;
        if (!flag) return;

        boolean nextFlag = false;
        int next = end;
        for (int i = start; i <= end; i++) {
            if (!nextFlag) {
                if (postorder[i] > center) {
                    nextFlag = true;
                    next = i;
                }
            } else {
                if (postorder[i] < center) {
                    flag = false;
                    return;
                }
            }
        }
        if(next == start || next == end){
            dfs(postorder, start, end - 1, postorder[end]);
        }else {
            if (next - 1 >= 0) dfs(postorder, start, next - 2, postorder[next - 1]);
            if (end - 1 >= 0) dfs(postorder, next, end - 2, postorder[end - 1]);
        }
    }

    public static void main(String args[]){
        offer33_verifyPostorder obj = new offer33_verifyPostorder();
        System.out.println(obj.verifyPostorder(new int[]{1,6,3,2,5}));
        System.out.println(obj.verifyPostorder(new int[]{1,3,2,6,5}));
        System.out.println(obj.verifyPostorder(new int[]{3,10,6,9,2}));
    }
}
