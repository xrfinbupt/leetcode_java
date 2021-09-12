package DFS_BFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xurongfei
 * @Date 2021/9/12
 * <p>
 * 784. 字母大小写全排列
 * <p>
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 * <p>
 * 输入：S = "12345"
 * 输出：["12345"]
 * <p>
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 */
public class no784_letter_case_permutation {
    private List<String> result = new ArrayList<>();

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了54.75%的用户
     */
    public List<String> letterCasePermutation(String s) {
        if (s == null || s.isEmpty()) return result;

        char[] orgArray = s.toCharArray();
        char[] array = new char[orgArray.length];

        dfs(orgArray, array, 0);

        return result;
    }

    private void dfs(char[] orgaArray, char[] array, int level) {
        if (level >= orgaArray.length) {
            result.add(new String(array));
            return;
        }
        char target = orgaArray[level];

        array[level] = target;
        dfs(orgaArray, array, level + 1);
        if (target >= 'a' && target <= 'z') {
            array[level] = (char) (target + 'A' - 'a');
            dfs(orgaArray, array, level + 1);
        } else if (target >= 'A' && target <= 'Z') {
            array[level] = (char) (target - 'A' + 'a');
            dfs(orgaArray, array, level + 1);
        }
    }
}
