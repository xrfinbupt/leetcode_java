package Array;

/**
 * 1391. 检查网格中是否存在有效路径
 *
 * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
 *
 * 1 表示连接左单元格和右单元格的街道。
 * 2 表示连接上单元格和下单元格的街道。
 * 3 表示连接左单元格和下单元格的街道。
 * 4 表示连接右单元格和下单元格的街道。
 * 5 表示连接左单元格和上单元格的街道。
 * 6 表示连接右单元格和上单元格的街道。
 *
 * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
 * 注意：你 不能 变更街道。
 * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
 *
 * 示例 1：
 * 输入：grid = [[2,4,3],[6,5,2]]
 * 输出：true
 * 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
 *
 * 示例 2：
 * 输入：grid = [[1,2,1],[1,2,1]]
 * 输出：false
 * 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
 *
 * 示例 3：
 * 输入：grid = [[1,1,2]]
 * 输出：false
 * 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
 *
 * 示例 4：
 * 输入：grid = [[1,1,1,1,1,1,3]]
 * 输出：true
 *
 * 示例 5：
 * 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
 * 输出：true
 *  
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * 1 <= grid[i][j] <= 6
 *
 * 链接：https://leetcode-cn.com/problems/check-if-there-is-a-valid-path-in-a-grid
 */
public class no1391_check_if_there_is_a_valid_path_in_a_grid {
    boolean[][] path;

    /**
     * 这道题错了好几次
     *
     * 执行用时：15 ms, 在所有 Java 提交中击败了76.22%的用户
     * 内存消耗：46.7 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param grid
     * @return
     */
    public boolean hasValidPath(int[][] grid) {
        boolean flag = hasValidPath1(grid,true);
        if(flag) return true;
        return hasValidPath1(grid,false);
    }
    public boolean hasValidPath1(int[][] grid,boolean flag) {
        int m = grid.length;
        int n = grid[0].length;
        int i = 0, j = 0;
        path = new boolean[m][n];

        boolean rightDir = true;
        boolean downDir = true;
        boolean upDir = false;
        boolean leftDir = false;
        while (i>=0 && i < m && j < n && j>=0) {
            if(path[i][j])return false;

            int val = grid[i][j];
            path[i][j] = true;
            boolean preRightDir = rightDir;
            boolean preDownDir = downDir;
            boolean preUpDir = upDir;
            boolean preLeftDir = leftDir;
            rightDir = canRightDir(preRightDir, preDownDir, preUpDir,preLeftDir, val);
            downDir = canDownDir(preRightDir, preDownDir, preUpDir, preLeftDir,val);
            upDir = canUpDir(preRightDir, preDownDir, preUpDir,preLeftDir, val);
            leftDir = canLeftDir(preRightDir, preDownDir, preUpDir,preLeftDir, val);
            if (i == 0 && j == 0 && val == 4) {
                rightDir = true;
                downDir = true;
            }
            if (i == m - 1 && j == n - 1) {
                if (rightDir || downDir || upDir || leftDir) return true;
                else if (val == 5) return true;
            }
            if (!rightDir && !downDir && !upDir && !leftDir) return false;

            if(flag){
                if (downDir) i++;
                else if (rightDir) j++;
                else if(leftDir) j--;
            }else{
                if (rightDir) j++;
                else if (downDir) i++;
                else if(leftDir) j--;
            }
            if (upDir) i--;
        }
        return false;
    }

    public boolean canRightDir(boolean rightDir, boolean downDir, boolean upDir,boolean leftDir, int val) {
        if (rightDir && val == 1) return true;
        if (downDir && val == 6) return true;
        if (upDir && val == 4) return true;

        return false;
    }
    public boolean canDownDir(boolean rightDir, boolean downDir, boolean upDir,boolean leftDir, int val) {
        if (rightDir && val == 3) return true;
        if (downDir && val == 2) return true;
        if(leftDir && val == 4) return true;

        return false;
    }

    public boolean canUpDir(boolean rightDir, boolean downDir, boolean upDir,boolean leftDir, int val) {
        if (rightDir && val == 5) return true;
        if (upDir && val == 2) return true;
        if(leftDir && val == 6) return true;

        return false;
    }

    public boolean canLeftDir(boolean rightDir, boolean downDir, boolean upDir,boolean leftDir, int val) {
        if (downDir && val == 5) return true;
        if (upDir && val == 3) return true;
        if(leftDir && val == 1) return true;

        return false;
    }

    public static void main(String args[]){
        no1391_check_if_there_is_a_valid_path_in_a_grid obj = new no1391_check_if_there_is_a_valid_path_in_a_grid();
        System.out.println("{2,4,3},{6,5,2}="+obj.hasValidPath(new int[][]{{2,4,3},{6,5,2}}));
        System.out.println("{2,4,3},{6,5,5="+obj.hasValidPath(new int[][]{{2,4,3},{6,5,5}}));
        System.out.println("1,2,2="+obj.hasValidPath(new int[][]{{1,1,2}}));
        System.out.println("1,1="+obj.hasValidPath(new int[][]{{1,1}}));
        System.out.println("1="+obj.hasValidPath(new int[][]{{1}}));
        System.out.println("2="+obj.hasValidPath(new int[][]{{2}}));
        System.out.println("3="+obj.hasValidPath(new int[][]{{3}}));
        System.out.println("4="+obj.hasValidPath(new int[][]{{4}}));
        System.out.println("5="+obj.hasValidPath(new int[][]{{5}}));
        System.out.println("6="+obj.hasValidPath(new int[][]{{6}}));
        System.out.println("4,1,3="+obj.hasValidPath(new int[][]{{4,1,3}}));
        System.out.println("{4,1},{6,1}="+obj.hasValidPath(new int[][]{{4,1},{6,1}}));
        System.out.println("{4,3},{6,1}="+obj.hasValidPath(new int[][]{{4,3},{6,1}}));
        System.out.println("{4,3},{6,2}="+obj.hasValidPath(new int[][]{{4,3},{6,2}}));
        System.out.println("{4,3,3},{6,5,2}="+obj.hasValidPath(new int[][]{{4,3,3},{6,5,2}}));
        int[][] data = new int[][]{
                {2,3,6,5,6,1,6,6},
                {5,6,3,5,1,5,4,2},
                {5,3,6,1,4,1,6,3},
                {2,2,4,2,5,6,2,3},
                {2,2,2,4,6,2,4,5},
                {1,6,5,6,4,2,4,6},
                {2,2,6,5,1,3,6,6},
                {6,5,2,3,2,3,2,6},
                {2,2,3,3,3,3,6,1},
                {5,3,3,2,2,2,1,1}};
        System.out.println("="+obj.hasValidPath(data));
    }
}
