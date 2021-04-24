package lcci;

/**
 * 面试题 16.04. 井字游戏
 *
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 示例 1：
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 *["OX ","OX ","O  "]
 * 示例 2：
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 *
 * 示例 3：
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 *
 * 提示：
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 *
 * 链接：https://leetcode-cn.com/problems/tic-tac-toe-lcci
 *
 *
 */
public class tic_tac_toe_lcci {
    /**
     * 这道题 错了好几次 反思一下
     *
     * 还有一种求和的思路
     * https://leetcode-cn.com/problems/tic-tac-toe-lcci/solution/qiu-he-de-si-xiang-java-ban-jia-zhu-shi-by-noscall/
     *
     * @param board
     * @return
     */
    public String tictactoe(String[] board) {
        int row = board.length;
        int col = board[0].length();

        if(row == 1) return board[0].trim().isEmpty()?"Pending":board[0];

        // row
        for(int i=0;i<row;i++){
            String str = board[i];
            char ch = str.charAt(0);
            if(ch != ' '){
                for(int j=1;j<col;){
                    char ch1 = str.charAt(j);
                    if(ch!=ch1){
                        break;
                    }
                    j++;
                    if(j == col){
                        return ch+"";
                    }
                }
            }
        }
        // col
        String str = board[0];
        for (int i = 0; i < col; i++) {
            char ch = str.charAt(i);

            if (ch != ' ') {
                for (int j = 1; j < row; ) {
                    String str1 = board[j];
                    char ch1 = str1.charAt(i);
                    if (ch != ch1) {
                        break;
                    }
                    j++;
                    if (j == row) {
                        return ch + "";
                    }
                }
            }
        }

        // corner 1
        str = board[0];
        char ch = str.charAt(0);
        if(ch != ' '){
            for(int i=1;i<row;){
                String str1 = board[i];
                char ch1 = str1.charAt(i);
                if(ch!=ch1){
                    break;
                }
                i++;
                if(i == row){
                    return ch+"";
                }
            }
        }

        // corner 2
        str = board[0];
        int j = str.length();
        ch = str.charAt(j-1);
        if(ch != ' '){
            for(int i=1;i<row;){
                String str1 = board[i];
                char ch1 = str1.charAt(j-1-i);
                if(ch!=ch1){
                    break;
                }
                i++;
                if(i == row){
                    return ch+"";
                }
            }
        }

        for(int i=0;i<row;i++){
             str = board[i];
             if(str.contains(" ")) return "Pending";
        }

        return "Draw";
    }
}
