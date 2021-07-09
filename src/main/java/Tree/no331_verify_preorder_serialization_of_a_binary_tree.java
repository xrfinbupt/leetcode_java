package Tree;

/**
 * 331. 验证二叉树的前序序列化 (Medium)
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 *
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 *
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 *
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 *
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 *
 * 示例 1:
 *
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * 示例 2:
 *
 * 输入: "1,#"
 * 输出: false
 * 示例 3:
 *
 * 输入: "9,#,#,1"
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
 *
 */
public class no331_verify_preorder_serialization_of_a_binary_tree {
    int index = 0;

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了76.78%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了29.49%的用户
     */
    public boolean isValidSerialization(String preorder) {
        if(preorder == null || preorder.isEmpty()) return true;

        String array[] = preorder.split(",");
        boolean result = dfs(array,0);

        return result && index == array.length;
    }
    private boolean dfs(String array[],int level){
        if(index >= array.length) return false;
        if(array[index++].equals("#")) return true;

        // left
        boolean leftResult = dfs(array,level+1);

        // right
        boolean rightResult =dfs(array,level+1);

        return leftResult && rightResult;
    }
    public static void main(String args[]){
        no331_verify_preorder_serialization_of_a_binary_tree obj = new no331_verify_preorder_serialization_of_a_binary_tree();
        obj.index = 0;
        System.out.println(obj.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));

        obj.index = 0;
        System.out.println(obj.isValidSerialization("1,#"));

        obj.index = 0;
        System.out.println(obj.isValidSerialization("1,#,#"));

        obj.index = 0;
        System.out.println(obj.isValidSerialization("9,#,#,1"));
    }
}
