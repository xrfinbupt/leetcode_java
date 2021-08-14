package lcof_offer;

/**
 * 剑指 Offer 05. 替换空格
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 * 限制：
 * 0 <= s 的长度 <= 10000
 *
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 */
public class offer5_replaceSpace {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了83.83%的用户
     */
    public String replaceSpace(String s) {
        if(s.isEmpty()) return s;

        int len = s.length();
        StringBuilder result = new StringBuilder();
        for(int i=0;i<len;i++){
            char ch = s.charAt(i);
            if(ch==' '){
                result.append("%20");
            }else{
                result.append(ch);
            }
        }
        return result.toString();
    }
}
