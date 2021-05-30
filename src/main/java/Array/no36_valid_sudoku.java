package Array;

import java.util.HashMap;
import java.util.Map;

/**
 * 36. 有效的数独
 *
 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 *
 * 示例 1：
 * 输入：board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：true
 *
 * 示例 2：
 * 输入：board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：false
 * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *  
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 *
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 */
public class no36_valid_sudoku {
    /**
     * 其实 每个模块 计算有重复的元素 即可返回false
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        Map<Integer,Integer> countMap = new HashMap<>();
        for(int i =1;i<=9;i++) countMap.put(i,1);

        for (int i = 0; i < m; i++) {
            Map<Integer,Integer> tempMap = new HashMap<>(countMap);
            for (int j = 0; j < n; j++) {
                if (i % 3 == 0 && j % 3 == 0) {
                    Map<Integer, Integer> localMap = new HashMap<>(countMap);
                    for (int ii = i; ii < i+3; ii++) {
                        for (int jj = j; jj < j+3; jj++) {
                            char ch = board[ii][jj];
                            if (ch == '.') continue;
                            int count = localMap.get(ch - '0');
                            if (count < 1) return false;
                            localMap.put(ch - '0', count - 1);
                        }
                    }
                }

                if (i == 0) {
                    Map<Integer, Integer> localMap = new HashMap<>(countMap);
                    for (int ii = 0; ii < m; ii++) {
                        char ch = board[ii][j];
                        if (ch == '.') continue;
                        int count = localMap.get(ch - '0');
                        if (count < 1) return false;
                        localMap.put(ch - '0', count - 1);
                    }
                }

                char ch = board[i][j];
                if (ch == '.') continue;
                int count = tempMap.get(ch - '0');
                if (count < 1) return false;
                tempMap.put(ch - '0', count - 1);
            }
        }

        return true;
    }
}
