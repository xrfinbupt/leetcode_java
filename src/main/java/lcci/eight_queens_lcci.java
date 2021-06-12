package lcci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题 08.12. 八皇后 (和no51_n_queens题目类似)
 *
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 *
 * 注意：本题相对原题做了扩展
 *
 * 示例:
 *  输入：4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/eight-queens-lcci
 */
public class eight_queens_lcci {
    List<List<String>> result = null;

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了63.64%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了92.93%的用户
     */
    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        if(n==1){
            result.add(Arrays.asList("Q"));
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
            List<String> tempResult = new ArrayList<>();
            for(int i=0;i<n;i++){
                StringBuilder temp = new StringBuilder();
                for(int j=0;j<n;j++){
                    if(queueFlags[i][j]){
                        temp.append("Q");
                    }else{
                        temp.append(".");
                    }
                }
                tempResult.add(temp.toString());
            }
            result.add(tempResult);
            return ;
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
