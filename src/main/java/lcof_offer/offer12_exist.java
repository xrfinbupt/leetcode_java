package lcof_offer;

/**
 * 剑指 Offer 12. 矩阵中的路径
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *  
 * 提示：
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
 * <p>
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 */
public class offer12_exist {
    private boolean flag = false;
    private int m, n;

    /**
     * 执行用时：81 ms, 在所有 Java 提交中击败了78.65%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了83.15%的用户
     */
    public boolean exist(char[][] board, String word) {
        flag = false;
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != word.charAt(0)) continue;

                dfs(board, i, j, word, 0);
                if (flag) return true;
            }
        }
        return flag;
    }

    private void dfs(char[][] board, int i, int j, String word, int pos) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == 0 || flag) return;
        char target = word.charAt(pos);
        if (board[i][j] != target) {
            return;
        }
        if (pos == word.length() - 1) {
            flag = true;
            return;
        }
        board[i][j] = 0;

        dfs(board, i - 1, j, word, pos + 1);

        dfs(board, i + 1, j, word, pos + 1);

        dfs(board, i, j - 1, word, pos + 1);

        dfs(board, i, j + 1, word, pos + 1);

        board[i][j] = target;
    }
    public static void main(String[] args){
        offer12_exist obj = new offer12_exist();
        System.out.println(obj.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCEDC"));

        System.out.println(obj.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED"));

        System.out.println(obj.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCEDA"));
    }
}
