package String;


/**
 * 12. 整数转罗马数字
 *
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 *
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 *
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 *
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 *
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 提示：
 * 1 <= num <= 3999
 *
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 */
public class no12_integer_to_roman {
    char rule[][] = { {'I','V'},{'X','L'},{'C','D'},{'M','!'} };

    public String intToRoman(int num) {
        String result = "";

        int preBase = 1;
        int base = 10;
        for (int level = 1; num > 0; ) {
            int temp = num % base;
            char levelRule[] = rule[level - 1];
            if (temp == 9 * preBase) {
                result = levelRule[0] + "" + rule[level][0] + result;
            } else if (temp >= 5 * preBase) {
                temp = temp - 5 * preBase;
                String tempResult = levelRule[1] + "";
                if (temp > 0) {
                    while (temp > 0) {
                        tempResult += levelRule[0];
                        temp -= preBase;
                    }
                }
                result = tempResult + result;
            } else if (temp == 4 * preBase) {
                result = levelRule[0] + "" + levelRule[1] + result;
            } else {
                String tempResult = "";
                if (temp > 0) {
                    while (temp > 0) {
                        tempResult += levelRule[0];
                        temp -= preBase;
                    }
                }
                result = tempResult + result;
            }

            num = num - num % base;
            level++;
            preBase = base;
            base *= 10;
        }

        return result;
    }
    public static void main(String args[]){
        no12_integer_to_roman obj = new no12_integer_to_roman();
        System.out.println("3:"+obj.intToRoman(3));
        System.out.println("4:"+obj.intToRoman(4));
        System.out.println("5:"+obj.intToRoman(5));
        System.out.println("8:"+obj.intToRoman(8));
        System.out.println("9:"+obj.intToRoman(9));
        System.out.println("58:"+obj.intToRoman(58));
        System.out.println("581:"+obj.intToRoman(581));
        System.out.println("1994:"+obj.intToRoman(1994));

    }
}
