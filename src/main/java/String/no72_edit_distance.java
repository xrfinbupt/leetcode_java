package String;

/**
 * 72. 编辑距离 (困难)
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * 链接：https://leetcode-cn.com/problems/edit-distance
 */
public class no72_edit_distance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(m==0 || n == 0) return Math.max(m,n);

        int dp[][] = new int[m][n];
        boolean flag = false;
        int count = 0;
        char target = word2.charAt(0);
        for (int i = 0; i < m; i++) {
            char ch = word1.charAt(i);
            if (target == ch) {
                if (!flag) flag = true;
                else count++;
            } else count++;

            dp[i][0] = count;
        }

        count = 0;
        flag = false;
        target = word1.charAt(0);
        for (int i = 0; i < n; i++) {
            char ch = word2.charAt(i);
            if (target == ch) {
                if (!flag) flag = true;
                else count++;
            } else count++;

            dp[0][i] = count;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int left =  dp[i][j - 1] + 1 ;
                int up = dp[i - 1][j] + 1 ;
                int leftUp =  dp[i - 1][j - 1] + (word1.charAt(i) == word2.charAt(j) ? 0 : 1) ;
                dp[i][j] = Math.min(left, Math.min(up, leftUp));
            }
        }

        return dp[m - 1][n - 1];
    }
    public static void main(String args[]){
        no72_edit_distance obj = new no72_edit_distance();
        int count = obj.minDistance("horse","ros");
        System.out.println(count);
    }
}
