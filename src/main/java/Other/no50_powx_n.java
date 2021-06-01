package Other;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 50. Pow(x, n)
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^-2 = 1/2^2 = 1/4 = 0.25
 *  
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 *
 * 链接：https://leetcode-cn.com/problems/powx-n
 */
public class no50_powx_n {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.75%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了5.31%的用户
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(x==0) return 0;
        if(n==0 || x==1) return 1;

        boolean flag = true;
        long newN = n;
        if (newN < 0) {
            flag = false;
            newN = 0 - newN;
        }
        if(newN==1){
            if(!flag) return 1/x;
            return x;
        }
        if(x==-1){
            if(newN%2==0) return 1;
            else return -1;
        }

        ArrayList<Double> sumList = new ArrayList<>();
        HashMap<Integer,Integer> posMap = new HashMap<>();
        int pos = 0;
        int count = 1;
        double preSum = x;
        sumList.add(x);
        posMap.put(count,sumList.size()-1);
        boolean flag2 = true;
        int countSum = 0;
        while(newN>0){
            if (flag2 && ((count << 1) <= newN)) {
                preSum = preSum * sumList.get(pos);
                if(preSum==0) return 0;
                if(preSum >=Double.MAX_VALUE) break;

                count = count << 1;
                countSum = count;
                sumList.add(preSum);
                posMap.put(count,sumList.size()-1);
                pos++;
            }  else if (!flag2 && (countSum+(count << 1) <= newN)) {
                count = count << 1;
                countSum += count;
                preSum = preSum * sumList.get(posMap.get(count));
                if(preSum==0) return 0;
                if(preSum >=Double.MAX_VALUE) break;
            } else {
                flag2 = false;
                newN = newN - countSum;
                if(newN==0) break;

                if(posMap.containsKey((int)newN)){
                    preSum = preSum * sumList.get(posMap.get((int)newN));
                    break;
                }else {
                    count = 1;
                    countSum = count;
                    preSum = preSum * sumList.get(posMap.get(count));
                }
            }
        }

        if(!flag) return 1/preSum;

        return preSum;
    }
    public static void main(String args[]){
        no50_powx_n obj = new no50_powx_n();
        double result = obj.myPow(2, 9); //1024
        System.out.println("2^9:"+result);

        result = obj.myPow(8.95371, -1); //1024
        System.out.println("8.95371^-1:"+result);

        result = obj.myPow(8.95371, -2); //1024
        System.out.println("8.95371^-2:"+result);

        result = obj.myPow(8.95371, 1); //1024
        System.out.println("8.95371^1:"+result);

        result = obj.myPow(8.95371, 2); //1024
        System.out.println("8.95371^2:"+result);

        result = obj.myPow(2, 10); //1024
        System.out.println("2^10:"+result);

        result = obj.myPow(2, 12); // 4096
        System.out.println("2^12:"+result);

        result = obj.myPow(2, 13); // 4096
        System.out.println("2^13:"+result);

        result = obj.myPow(2, 14); // 16384
        System.out.println("2^14:"+result);

        result = obj.myPow(2, 16); // 16384
        System.out.println("2^16:"+result);

        result = obj.myPow(2, -6); // 16384
        System.out.println("2^-6:"+result);

        result = obj.myPow(2.10000, 3); // 16384
        System.out.println("2.1^3:"+result);

        result = obj.myPow(0.00001, 2147483647); // 16384
        System.out.println("0.00001^2147483647:"+result);

        result = obj.myPow(2, -2147483648); // 16384
        System.out.println("2^-2147483648:"+result);

    }
}
