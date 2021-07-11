package DFS;

/**
 * 130. 被围绕的区域(Medium)
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *  
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 */
public class no130_surrounded_regions {
    boolean[][] flagArray = null;

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了26.10%的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了20.60%的用户
     */
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        flagArray = new boolean[m][n];

        bfs(board,0);

        int result = 1;
        while(result>0) {
            result  =  processFlagArray(board);
        }

        for(int i = 0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if(flagArray[i][j] && board[i][j]=='O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void bfs(char[][] board,int deep){
        if (deep >= board.length) return;

        int n = board[0].length;

        for (int j = 0; j < n; j++) {
            boolean upFlag = deep - 1 >= 0 ? flagArray[deep - 1][j] : false;
            boolean leftFlag = j - 1 >= 0 ? flagArray[deep][j - 1] : false;
            boolean rightFlag = j == n -1 ? false : true;
            boolean bottomFlag = deep == board.length -1?false:true;
            if(board[deep][j] == 'X'){
                flagArray[deep][j] = true;
            }else{
                if(upFlag && leftFlag && rightFlag && bottomFlag) flagArray[deep][j] = true;
                else {
                    flagArray[deep][j] = false;
                }
            }
        }
        bfs(board,deep+1);
    }
    private int processFlagArray(char[][] board){
        int count = 0;
        int m = board.length;
        int n = board[0].length;

        for(int i = m-1;i>=0;i--){
            for (int j = n-1; j >=0; j--) {
                if (flagArray[i][j] == false) {
                    if (j - 1 >= 0 && flagArray[i][j - 1] && board[i][j - 1] == 'O') {
                        flagArray[i][j - 1] = false;
                        count++;
                    }
                    if (i - 1 >= 0 && flagArray[i - 1][j] && board[i - 1][j] == 'O') {
                        flagArray[i - 1][j] = false;
                        count++;
                    }
                    for(int jj=j+1;jj<=n-1;jj++){
                        if(flagArray[i][jj] && board[i][jj]=='O'){
                            flagArray[i][jj] = false;
                            count++;
                        }else{
                            break;
                        }
                    }
                    for(int ii=i+1;ii<=m-1;ii++){
                        if(flagArray[ii][j] && board[ii][j]=='O'){
                            flagArray[ii][j] = false;
                            count++;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String args[]){
        no130_surrounded_regions obj = new no130_surrounded_regions();
        char[][] data = new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}};
        obj.solve(data);
        System.out.println();

        data = new char[][]{{'X'}};
        obj.solve(data);
        System.out.println();

        data = new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','O'},
                {'X','O','X','X'}};
        obj.solve(data);
        System.out.println();
    }
}
