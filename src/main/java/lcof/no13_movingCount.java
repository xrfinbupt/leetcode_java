package lcof;

/**
 * 剑指 Offer 13. 机器人的运动范围
 * <p>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * <p>
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 */
public class no13_movingCount {
    boolean findPath[][];
    int mArray[];
    int nArray[];
    int sum = 0;

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了74.69%的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了41.19%的用户
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0) return 1;
        int max = Math.max(m, n);
        findPath = new boolean[m][n];
        mArray = new int[m];
        nArray = new int[n];
        sum = 0;
        for (int i = 0; i < max; i++) {
            int count = getCount(i);
            if (i < m) {
                mArray[i] = count;
            }
            if (i < n) {
                nArray[i] = count;
            }
        }

        dfs(0, m, 0, n, k);

        return sum;
    }

    public int getCount(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i = i / 10;
        }
        return sum;
    }

    private void dfs(int i, int m, int j, int n, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if(findPath[i][j]) return;

        int val = mArray[i] + nArray[j];
        if (val > k) return;
        sum++;
        findPath[i][j] = true;

        dfs(i-1,m,j,n,k);
        dfs(i+1,m,j,n,k);

        dfs(i,m,j-1,n,k);
        dfs(i,m,j+1,n,k);
    }

    public static void main(String args[]) {
        no13_movingCount obj = new no13_movingCount();
        int sum = obj.movingCount(2, 3, 1);
        System.out.println(sum);


        sum = obj.movingCount(3, 1, 0);
        System.out.println(sum);

        sum = obj.movingCount(100, 100, 20);
        System.out.println(sum);

        sum = obj.movingCount(100, 100, 10);
        System.out.println(sum);


        System.out.println(obj.getCount(100));
        System.out.println(obj.getCount(10));
        System.out.println(obj.getCount(99));
    }
}
