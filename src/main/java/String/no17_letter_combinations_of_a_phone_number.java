package String;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *  
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 *
 */
public class no17_letter_combinations_of_a_phone_number {
    List<String> result = new ArrayList<>();
    String rule[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户内存消耗：37.1 MB, 在所有 Java 提交中击败了80.35%的用户
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) return result;

        List<String> orgDatas = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            int index = (digits.charAt(i) - '0') - 2;
            orgDatas.add(rule[index]);
        }

        sub_letterCombinations(orgDatas, 0, new StringBuilder());

        return result;
    }

    /**
     * 非递归 queue
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        if(digits.length()==0) return result;

        List<String> orgDatas = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            int index = (digits.charAt(i) - '0') - 2;
            orgDatas.add(rule[index]);
        }

        int len = orgDatas.size();
        Queue<StringBuilder> queue = new LinkedList<>();
        String data = orgDatas.get(0);
        int subLen = data.length();
        for (int j = 0; j < subLen; j++) {
            queue.add(new StringBuilder().append(data.charAt(j)));
        }

        for (int i = 1; i < len; i++) {
            data = orgDatas.get(i);
            queue_letterCombinations(data,queue);
        }

        while(!queue.isEmpty()) {
            result.add(queue.remove().toString());
        }

        return result;
    }

    public void queue_letterCombinations(String data,Queue<StringBuilder> queue){
        int subLen = data.length();
        int queueSize = queue.size();
        while(queueSize-->0) {
            StringBuilder queueItem = queue.remove();
            for (int j = 0; j < subLen; j++) {
                StringBuilder iter = new StringBuilder(queueItem);
                iter.append(data.charAt(j));
                queue.add(iter);
            }
        }
    }

    public void sub_letterCombinations(List<String> orgDatas,int level,StringBuilder tempResult) {
        if (level == orgDatas.size()) {
            result.add(tempResult.toString());
            return;
        }

        String levelStr = orgDatas.get(level);
        for (int i = 0; i < levelStr.length(); i++) {
            char ch = levelStr.charAt(i);
            tempResult.append(ch);
            int pos = tempResult.length() -1 ;
            sub_letterCombinations(orgDatas, level + 1, tempResult);
            tempResult.deleteCharAt(pos);
        }
    }

    public static void main(String args[]) {
        no17_letter_combinations_of_a_phone_number obj = new no17_letter_combinations_of_a_phone_number();
        List<String> result = obj.letterCombinations("23");
        System.out.println(result);

        result = obj.letterCombinations("99");
        System.out.println(result);

    }
}
