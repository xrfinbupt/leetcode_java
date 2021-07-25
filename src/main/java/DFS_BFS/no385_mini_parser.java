package DFS_BFS;

import com.alibaba.fastjson.JSON;
import common.NestedInteger;

import java.util.*;

/**
 * 385. 迷你语法分析器
 *
 * 给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。
 * 列表中的每个元素只可能是整数或整数嵌套列表
 *
 * 提示：你可以假定这些字符串都是格式良好的：
 * 字符串非空
 * 字符串不包含空格
 * 字符串只包含数字0-9、[、-、,、]
 *  
 * 示例 1：
 * 给定 s = "324",
 * 你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
 *
 * 示例 2：
 * 给定 s = "[123,[456,[789]]]",
 * 返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
 *
 * 1. 一个 integer 包含值 123
 * 2. 一个包含两个元素的嵌套列表：
 *     i.  一个 integer 包含值 456
 *     ii. 一个包含一个元素的嵌套列表
 *          a. 一个 integer 包含值 789
 *
 * 链接：https://leetcode-cn.com/problems/mini-parser
 */
public class no385_mini_parser {
    private char[] charArray;
    int index = 0;

    /**
     * 参考 https://leetcode-cn.com/problems/mini-parser/solution/javadi-gui-2ms-100-by-codeband/
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了97.91%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了81.59%的用户
     */
    public NestedInteger deserialize(String s) {
        if(s.charAt(0)!='['){
            NestedInteger result = new NestedInteger(new Integer(s));
            return result;
        }

        index = 0;
        charArray = s.toCharArray();
        NestedInteger result = dfs();

        return result;
    }
    private NestedInteger dfs(){
        NestedInteger result = new NestedInteger();
        int sign = 1;
        int num = 0;
        while(charArray.length>index) {
            index++;
            if (charArray[index] == ',') {
                continue;
            }
            if (charArray[index] == '[') {
                result.add(dfs());
            }else if(charArray[index] == '-'){
                sign = -1;
            }else if(charArray[index] == ']'){
                return result;
            }else{
                num = 10*num + sign * (charArray[index] - '0');
                if(charArray[index+1]==','||charArray[index+1]==']'){
                    result.add(new NestedInteger(num));
                    sign =1;
                    num =0;
                }
            }
        }

        return result;
    }

    public static void main(String args[]){
        no385_mini_parser obj = new no385_mini_parser();
        String input = "324";
        NestedInteger result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));

        input = "[123]";
        result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));
        //expect [123]  实际 [123]

        input = "[123,[456]]";
        result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));
        //expect [123,[456]]  实际??

        input = "[123,456,789]";
        result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));
        //expect [123,456,789]  实际 [[789]]

        input = "[123,[456,[789]]]";
        result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));
        //expect [123,[456,[789]]]  实际 [[789]]

        input = "[324,[123],[124]]";
        result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));
        //expect [123,[456,[789]]]  实际 [123,124]

        input = "[324,[123,122,[124]]]";
        result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));
        //expect [324,[123,122,[124]]] 实际 [[[124]]]

        input = "[324,23,[123],[124]]";
        result =  obj.deserialize(input);
        System.out.println(input+"=="+JSON.toJSONString(result));
        //expect [324,23,[123],[124]] 实际 [[123,124]]
    }
}
