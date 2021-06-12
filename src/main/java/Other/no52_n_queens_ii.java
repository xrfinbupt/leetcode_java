package Other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 52. N 皇后
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * https://leetcode-cn.com/problems/n-queens-ii
 */
public class no52_n_queens_ii {
    int result = 0;

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了69.25%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了61.85%的用户
     */
    public int totalNQueens(int n) {
        if(n==1){
            result = 1;
            return result;
        }else if(n==2||n==3){
            return result;
        }else{
            boolean queueFlags[][] = new boolean[n][n];
            dfs(0,n,queueFlags);
        }

        return result;
    }

    public void dfs(int level,int n,boolean queueFlags[][]){
        if(level==n){
            result++;
            return;
        }
        if(level>n) return ;
        for(int i=0;i<n;i++){
            if(checkValid(queueFlags,level,i,n)){
                queueFlags[level][i] = true;
                dfs(level+1,n,queueFlags);
                queueFlags[level][i] = false;
            }
        }
    }
    // 不需要考虑 水平层
    public boolean checkValid(boolean queueFlags[][],int targetI,int targetJ,int n){
        for (int i = 0; i < n; i++) {
            if (i != targetI && queueFlags[i][targetJ]) {
                return false;
            }
        }
        int i=targetI,j=targetJ;
        while(i>=0 && j>=0 && i<n && j<n){
            if(queueFlags[i][j]) return false;
            i++;
            j++;
        }

        i=targetI;
        j=targetJ;
        while(i>=0 && j>=0 && i<n && j<n){
            if(queueFlags[i][j]) return false;
            i--;
            j--;
        }

        i=targetI;
        j=targetJ;
        while(i>=0 && j>=0 && i<n && j<n){
            if(queueFlags[i][j]) return false;
            i--;
            j++;
        }

        i=targetI;
        j=targetJ;
        while(i>=0 && j>=0 && i<n && j<n){
            if(queueFlags[i][j]) return false;
            i++;
            j--;
        }

        return true;
    }
}
