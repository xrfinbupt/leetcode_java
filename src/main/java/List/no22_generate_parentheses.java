package List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 22. 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *  
 * 提示：
 * 1 <= n <= 8
 *
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 *
 */
public class no22_generate_parentheses {
    /**
     * left right 好理解些
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        if(n==1) {
            result.add("()");
            return result;
        }
        method1(result,"",n,n);
        return result;
    }
    public void method1(List<String> list,String s,int left,int right){
        if(left == 0 && right == 0){
            list.add(s);
            return;
        }
        if(left >0){
            method1(list,s+"(",left-1,right);
        }
        if(right > left){
            method1(list,s+")",left,right-1);
            return;
        }
    }

    /**
     *
     * @param list
     * @param s
     * @param n 还剩多少元素代放
     * @param num 做括号大于右括号的个数
     */
    public void method2(List<String> list,String s,int n,int num){
        if(n == 0 ){
            list.add(s);
            return;
        }
        if(n >= num+2 && num>=1){
            method2(list,s+"(",n-1,num+1);
            method2(list,s+")",n-1,num-1);
        }else if(num == 0){
            method2(list,s+"(",n-1,num+1);
        }else{
            method2(list,s+")",n-1,num-1);
        }
    }
}
