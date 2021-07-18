package DFS_BFS;

/**
 * 329. 矩阵中的最长递增路径
 *
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 *
 * 示例 1：
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 *
 * 示例 2：
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：1
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 *
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 */
public class no329_longest_increasing_path_in_a_matrix {
    int[][] dp = null;
    int m,n;

    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.41%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了52.34%的用户
     */
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        dp = new int[m][n];

        int max = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int longPath = dfs(matrix,i,j,1);
                max = Math.max(max,longPath);
            }
        }

        return max;
    }
    private int dfs(int[][] matrix,int i,int j,int path){
        int cachePath = dp[i][j];
        if(cachePath!=0){
            return cachePath;
        }

        int iUp = i==0?0:i-1;
        int iDown = i==m-1?m-1:i+1;
        int jLeft = j==0?0:j-1;
        int jRight = j==n-1?n-1:j+1;

        int orgPath = path;
        int pathTemp = path;
        if(matrix[iUp][j] > matrix[i][j]){
            pathTemp = dfs(matrix,iUp,j,1);
            path = Math.max(pathTemp+orgPath,path);
        }

        if(matrix[iDown][j] > matrix[i][j]){
            pathTemp = dfs(matrix,iDown,j,1);
            path = Math.max(pathTemp+orgPath,path);
        }

        if(matrix[i][jLeft] > matrix[i][j]){
            pathTemp = dfs(matrix,i,jLeft,1);
            path = Math.max(pathTemp+orgPath,path);
        }

        if(matrix[i][jRight] > matrix[i][j]){
            pathTemp = dfs(matrix,i,jRight,1);
            path = Math.max(pathTemp+orgPath,path);
        }

        dp[i][j] = path;
        return path;
    }

    public static void main(String args[]){
        no329_longest_increasing_path_in_a_matrix obj = new no329_longest_increasing_path_in_a_matrix();
        int res = obj.longestIncreasingPath(new int[][]{
                {9,9,4},{6,6,8},{2,1,1}
        });
        System.out.println(res);

        res = obj.longestIncreasingPath(new int[][]{
                {3,4,5},{3,2,6},{2,2,1}
        });
        System.out.println(res);

        res = obj.longestIncreasingPath(new int[][]{
                {5}
        });
        System.out.println(res);
    }
}
