package lcci;

/**
 * 面试题 16.18. 模式匹配
 *
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 *
 * 示例 1：
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 *
 * 示例 2：
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 *
 * 示例 3：
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 *
 * 示例 4：
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 *
 * 提示：
 * 1 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 *
 * 链接：https://leetcode-cn.com/problems/pattern-matching-lcci
 */
public class pattern_matching_lcci {
    /**
     * 这道题想了好久
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了95.54%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了56.93%的用户
     *
     * @param pattern
     * @param value
     * @return
     */
    public boolean patternMatching(String pattern, String value) {
        if(pattern.length()==1) return true;

        int len = value.length();
        int aCount = 0,bCount = 0;
        for (char ch : pattern.toCharArray()) {
            if (ch == 'a') aCount++;
            else bCount++;
        }
        if((aCount == 0 || bCount == 0) && value.isEmpty()) return true;

        int aStart = 0,aEnd = (aCount==0)?0:len/aCount;
        if(bCount==0) aStart = len/aCount;

        for (int aLen = aStart; aLen <= aEnd; aLen++) {
            int bLen = 0;
            if (bCount > 0) {
                bLen = (len - aLen * aCount) / bCount;
            }
            if(aLen*aCount + bLen*bCount != len) continue;
            int pos = 0;
            String preA = "", preB = "";
            boolean flagA = true, flagB = true;
            for (char ch : pattern.toCharArray()) {
                if (ch == 'a') {
                    if(aLen==0) continue;
                    if (flagA) {
                        preA = value.substring(pos, pos + aLen);
                        flagA = false;
                    } else {
                        String curr = value.substring(pos, pos + aLen);
                        if (!preA.equals(curr)) break;
                    }
                    pos += aLen;
                } else {
                    if(bLen==0) continue;
                    if (flagB) {
                        preB = value.substring(pos, pos + bLen);
                        flagB = false;
                    } else {
                        String curr = value.substring(pos, pos + bLen);
                        if (!preB.equals(curr)) break;
                    }
                    pos += bLen;
                }
            }
            if(pos==len && !preA.equals(preB)) return true;
        }

        return false;
    }

    public static void main(String args[]){
        pattern_matching_lcci obj = new pattern_matching_lcci();
        System.out.println("\"abba\",\"\":"+obj.patternMatching("abba",""));
        System.out.println("\"aa\",\"\":"+obj.patternMatching("aa",""));
        System.out.println("\"aa\",\"ab\":"+obj.patternMatching("aa","ab"));
        System.out.println("\"aa\",\"aa\":"+obj.patternMatching("aa","aa"));
        System.out.println("\"bb\",\"hellohello\":"+obj.patternMatching("bb","hellohello"));

        System.out.println("\"abba\",\"dogdogdogdog\":"+obj.patternMatching("abba","dogdogdogdog"));

        System.out.println("\"abba\",\"dogcatcatdog\":"+obj.patternMatching("abba","dogcatcatdog"));
        System.out.println("\"abba\",\"dogcatcatfish\":"+obj.patternMatching("abba","dogcatcatfish"));
        System.out.println("\"aaaa\",\"dogcatcatdog\":"+obj.patternMatching("aaaa","dogcatcatdog"));
        System.out.println("\"bbbb\",\"catcatcatcat\":"+obj.patternMatching("bbbb","catcatcatcat"));
        System.out.println("\"abba\",\"dogdogdogdog\":"+obj.patternMatching("abba","dogdogdogdog"));
    }
}
