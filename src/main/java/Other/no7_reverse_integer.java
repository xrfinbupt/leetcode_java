package Other;

/**
 * 7. 整数反转
 *
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回0
 * 假设环境不允许存储 64 位整数（有符号或无符号）
 *  
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 * 提示：
 * -231 <= x <= 231 - 1
 *
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 */
public class no7_reverse_integer {
    public int reverse(int x) {
        String temp = ""+x;
        long result = 0;
        long tempResult = 0;
        for(int i=temp.length()-1;i>=0;i--){
            char ch = temp.charAt(i);
            if(result == 0 && ch == '0') continue;
            if(ch == '-') {
                result = 0 - result;
                break;
            }

            if(result==0){
                result = (ch - '0');
            }else{
                tempResult = result;
                tempResult = tempResult*10;
                if(tempResult<result){
                    return 0;
                }
                result =tempResult +  (ch - '0');
            }
        }

        if(result<Integer.MIN_VALUE || result>Integer.MAX_VALUE){
            return 0;
        }
        return (int)result;
    }

    public static void main(String args[]){
        no7_reverse_integer obj = new no7_reverse_integer();
        int result = obj.reverse(1534236469);

        System.out.println("result:"+result);
    }
}
