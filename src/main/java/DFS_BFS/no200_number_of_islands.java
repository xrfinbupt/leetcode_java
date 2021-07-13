package DFS_BFS;

import java.util.*;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *  
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 */
public class no200_number_of_islands {
    /**
     * 执行时间 有点低 比预期低
     * 执行用时：5 ms, 在所有 Java 提交中击败了21.97%的用户
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int num = 0;
        int index = 0;
        int m = grid.length;
        int n = grid[0].length;
        int array[][] = new int[m][n];
        HashMap<Integer, Set<Integer>> relationMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = array[i - 1][j];
                }
                if (j > 0) {
                    left = array[i][j - 1];
                }

                if (grid[i][j] == '1') {
                    if (left == 0 && up == 0) {
                        num++;
                        index++;
                        array[i][j] = index;
                    } else if (left > 0 && up > 0) {
                        if (left == up) {
                            array[i][j] = left;
                        } else {
                            array[i][j] = left;
                            Set<Integer> dataleft = relationMap.getOrDefault(left, new HashSet<Integer>());
                            Set<Integer> dataup = relationMap.getOrDefault(up, new HashSet<Integer>());
                            boolean flag = false;
                            for(Integer iter:dataleft){
                                if(dataup.contains(iter)){
                                    flag = true;
                                    break;
                                }
                            }
                            if(!flag){
                                dataleft.add(left);
                                dataleft.add(up);
                                dataleft.addAll(dataup);
                                relationMap.put(left, dataleft);

                                dataup.add(up);
                                dataup.add(left);
                                dataup.addAll(dataleft);
                                relationMap.put(up, dataup);
                                num--;
                            }
                        }
                    } else if (left > 0 || up > 0) {
                        array[i][j] = Math.max(left, up);
                    }
                }
            }
            if(i==m-1){
                for(int ii=0;ii<m;ii++){
                    for(int jj=0;jj<n;jj++){
                        System.out.print(array[ii][jj] + " ");
                    }
                    System.out.println();
                }
                System.out.println("------------");
                System.out.println(num);
            }
        }

        return num;
    }

    /**
     * 作者：LXUPByxLkE
     * 链接：https://leetcode-cn.com/problems/number-of-islands/solution/zhi-xing-yong-shi-1-ms-zai-suo-you-java-9o2n0/
     * 感觉这种方式 char最大值 255 不满足约束条件 1 <= m, n <= 300
     * */
    public int numIslands2(char[][] grid) {
        int line = grid.length;
        int column = grid[0].length;
        // 由于1表示陆地，所以从2开始按顺序编号探索到的岛屿
        int number = 1;
        // 按顺序探索过程中，前面探索到的岛屿可能会被新探索的陆地连接，此变量记录被连接次数，
        // 最后岛屿编号减1再减去该变量就是总岛屿数
        int subtract = 0;
        for (int i = 0; i < line; i++) { // 从左到右、从上到下探索大陆
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') { // 陆地
                    char up = i == 0 ? '0' : grid[i - 1][j]; // 上
                    char left = j == 0 ? '0' : grid[i][j - 1]; // 左
                    if (up == '0') { // 上是水
                        if (left == '0') { // 左也是水
                            // 上左都是水，目前是独立岛屿，赋予新编号
                            number++;
                            grid[i][j] = (char)(number + 48); // 新编号岛屿
                        } else { // 左是陆地
                            grid[i][j] = left; // 当前陆地划给左岛屿
                        }

                    } else { // 上是陆地
                        grid[i][j] = up; // 当前陆地划给上岛屿
                        if (left != '0') { // 左也是陆地
                            if (up != left) { // 上左不是同一编号岛屿，被当前陆地连接，左岛屿划给上岛屿（编号被上岛屿同化）
                                subtract++; // 岛屿数减1
                                for (int k = 0; k < line; k++) {
                                    // 这里k<line改成k<=i耗时反而多了1ms，i+1行后的大陆都未探索也未编号，无需遍历的，为什么不遍历耗时反而更高？
                                    // 请问为什么？？？是系统问题吗
                                    for (int l = 0; l < column; l++) {
                                        if (grid[k][l] == left) {
                                            grid[k][l] = up;
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        return number - 1 - subtract;
    }

    /**
     * 广度优先遍历 参考官网
     *
     * @param grid
     * @return
     */
    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    // 发现陆地后 把陆地所有的标志都clear 上下左右
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了61.20%的用户
     */
    int m,n;
    public int numIslands4(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int num = 0;
        for(int i=0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    num++;
                }
            }
        }
        return num;
    }
    public void dfs(char[][] grid,int row,int col) {
        if(row <0 || row >=m || col <0 || col >=n || grid[row][col]!='1'){
            return;
        }
        grid[row][col] = 'A';
        dfs(grid,row-1,col);
        dfs(grid,row+1,col);
        dfs(grid,row,col-1);
        dfs(grid,row,col+1);
    }

    public static void main(String args[]) {
        no200_number_of_islands obj = new no200_number_of_islands();
        char data[][] = new char[][]{{'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int result = obj.numIslands(data);
        System.out.println(result);
    }
}
