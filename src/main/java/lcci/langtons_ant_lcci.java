package lcci;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 面试题 16.22. 兰顿蚂蚁
 *
 * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
 *
 * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
 * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
 *
 * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
 *
 * 网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，分别表示蚂蚁 左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
 *
 * 示例 1:
 * 输入: 0
 * 输出: ["R"]
 *
 * 示例 2:
 * 输入: 2
 * 输出:
 * [
 *   "_X",
 *   "LX"
 * ]
 *
 * 示例 3:
 * 输入: 5
 * 输出:
 * [
 *   "_U",
 *   "X_",
 *   "XX"
 * ]
 * 说明：
 * K <= 100000
 *
 * 链接：https://leetcode-cn.com/problems/langtons-ant-lcci
 */
public class langtons_ant_lcci {
    final static String Left = "L",Right = "R",Up = "U",Down = "D";
    final static String Black = "X",White = "_";

    /**
     * 执行用时：544 ms, 在所有 Java 提交中击败了13.76%的用户
     * 内存消耗：58.8 MB, 在所有 Java 提交中击败了54.65%的用户
     *
     * @param K
     * @return
     */
    public List<String> printKMoves(int K) {
        if(K<0) return null;
        List<String> result = new ArrayList<>();
        if(K==0) {
            result.add("R");
            return result;
        }
        int i = 0, j = 0;
        int minI = 0, maxI = 0, minJ = 0, maxJ = 0;

        // key: i:j  value:X or _
        HashMap<String, String> map = new HashMap<>();

        int step = 0;
        String dir = Right;
        while(step<K){
            String key = i+":"+j;
            String val = map.getOrDefault(key,White);
            map.put(key,val.equals(White)?Black:White);

            if(val.equals(White)) {
                if(dir.equals(Right)){
                    dir = Down;
                    i++;
                }else if(dir.equals(Down)){
                    dir = Left;
                    j--;
                }else if(dir.equals(Left)){
                    dir = Up;
                    i--;
                }else{
                    dir = Right;
                    j++;
                }
            }else{
                if(dir.equals(Right)){
                    dir = Up;
                    i--;
                }else if(dir.equals(Up)){
                    dir = Left;
                    j--;
                }else if(dir.equals(Left)){
                    dir = Down;
                    i++;
                }else{
                    dir = Right;
                    j++;
                }
            }

            step++;
            minI = Math.min(minI,i);
            maxI = Math.max(maxI,i);
            minJ = Math.min(minJ,j);
            maxJ = Math.max(maxJ,j);

            if(step ==K){
                key = i + ":" + j;
                map.put(key,dir);
            }
        }

        for(i=minI;i<=maxI;i++){
            StringBuilder temp = new StringBuilder();
            for(j=minJ;j<=maxJ;j++){
                String key = i + ":" + j;
                String val = map.getOrDefault(key,White);
                temp.append(val);
            }
            result.add(temp.toString());
        }

        return result;
    }

    public static void main(String args[]){
        langtons_ant_lcci obj = new langtons_ant_lcci();
        System.out.println("0="+JSON.toJSONString(obj.printKMoves(0)));
        System.out.println("1="+JSON.toJSONString(obj.printKMoves(1)));
        System.out.println("2="+JSON.toJSONString(obj.printKMoves(2)));
        System.out.println("3="+JSON.toJSONString(obj.printKMoves(3)));
        System.out.println("4="+JSON.toJSONString(obj.printKMoves(4)));
        System.out.println("5="+JSON.toJSONString(obj.printKMoves(5)));
        System.out.println("10="+JSON.toJSONString(obj.printKMoves(10)));
        System.out.println("100="+JSON.toJSONString(obj.printKMoves(100)));
        System.out.println("1000="+JSON.toJSONString(obj.printKMoves(1000)));
    }
}
