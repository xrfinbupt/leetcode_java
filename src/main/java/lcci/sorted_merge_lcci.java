package lcci;

/**
 * 面试题 10.01. 合并排序的数组
 *
 * https://leetcode-cn.com/problems/sorted-merge-lcci/
 */
public class sorted_merge_lcci {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int p = m+n-1;
        while(p>=0){
            if(n-1 >= 0 && m -1 >=0 ){
                if(A[m-1]>= B[n-1]) {
                    A[p] = A[m - 1];
                    m--;
                }else{
                    A[p] = B[n-1];
                    n--;
                }
                p--;
            }else {
                break;
            }
        }
        while(m-1>=0){
            A[p] = A[m - 1];
            m--;
            p--;
        }
        while(n-1>=0){
            A[p] = B[n - 1];
            n--;
            p--;
        }
    }
}
