package String;

import java.util.HashMap;
import java.util.Map;

public class no13_roman_to_integer {
    //                 1   4    5   9   10   40  50   90  100  400 500 900  1000
    String rule[] = { "I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M" };
    int rule2[] = {1,4,5,9,10,40,50,90,100,400,500,900,1000};

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        for (String iter : rule) {
            map.put(iter, rule2[i]);
            i++;
        }
        int result = 0;
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (j + 1 < s.length()) {
                char ch2 = s.charAt(j + 1);
                Integer temp = map.get(ch + "" + ch2);
                if (temp != null) {
                    result += temp;
                    j++;
                } else {
                    result += map.get(ch + "");
                }
            } else {
                result += map.get(ch + "");
            }
        }

        return result;
    }
    public static void main(String args[]){
        no13_roman_to_integer obj = new no13_roman_to_integer();
        System.out.println("III:"+obj.romanToInt("III"));
        System.out.println("IV:"+obj.romanToInt("IV"));
        System.out.println("IX:"+obj.romanToInt("IX"));
        System.out.println("LVIII:"+obj.romanToInt("LVIII"));
        System.out.println("MCMXCIV:"+obj.romanToInt("MCMXCIV"));
    }
}
