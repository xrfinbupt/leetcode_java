package lcof_offer;

/**
 * @author xurongfei
 * @Date 2021/9/21
 *
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 *
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 * 限制：
 * 1 <= target <= 10^5
 *
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 */
public class offer57_findContinuousSequence {

    public int[][] findContinuousSequence(int target) {
        double diff = 1.5;
        for(int i=2;i<100;i++){
            System.out.println(diff+"x"+i +"=" + diff*i);
            diff+=0.5;
        }
        return null;
    }
    public static void main(String[] args){
        offer57_findContinuousSequence obj = new offer57_findContinuousSequence();
        obj.findContinuousSequence(100);
    }
}
