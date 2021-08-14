package DFS_BFS;

import lcof_offer.offer12_exist;

/**
 * 79. 单词搜索
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *  
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-search
 */
public class no79_word_search {
    private boolean flag = false;
    private int m, n;

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了99.35%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了64.90%的用户
     */
    public boolean exist(char[][] board, String word) {
        flag = false;
        m = board.length;
        n = board[0].length;

        if(m*n < word.length()){
            return false;
        }
        char[] words = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != word.charAt(0)) continue;

                dfs(board, i, j, words, 0);
                if (flag) return true;
            }
        }
        return flag;
    }

    private void dfs(char[][] board, int i, int j, char[] words, int pos) {
        if (i < 0 || i >= m || j < 0 || j >= n || flag || board[i][j] == 0 || board[i][j] != words[pos]) {
            return;
        }

        char target = words[pos];
        if (pos == words.length - 1) {
            flag = true;
            return;
        }
        board[i][j] = 0;
        dfs(board, i - 1, j, words, pos + 1);
        dfs(board, i + 1, j, words, pos + 1);
        dfs(board, i, j - 1, words, pos + 1);
        dfs(board, i, j + 1, words, pos + 1);
        board[i][j] = target;
    }
    public static void main(String[] args){
        no79_word_search obj = new no79_word_search();
        System.out.println(obj.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCEDC"));

        System.out.println(obj.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED"));

        System.out.println(obj.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCEDA"));
        System.out.println(obj.exist(new char[][]{{'A','A','A','A'},{'A','A','A','A'},{'A','A','A','A'}},"AAAAAAAAAAAA"));
    }
}
