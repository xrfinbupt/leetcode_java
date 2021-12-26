package WeeklyContest;

import com.alibaba.fastjson.JSON;

/**
 * 5964. 执行所有后缀指令
 * 现有一个 n x n 大小的网格，左上角单元格坐标 (0, 0) ，右下角单元格坐标 (n - 1, n - 1) 。给你整数 n 和一个整数数组 startPos ，其中 startPos = [startrow, startcol] 表示机器人最开始在坐标为 (startrow, startcol) 的单元格上。
 * <p>
 * 另给你一个长度为 m 、下标从 0 开始的字符串 s ，其中 s[i] 是对机器人的第 i 条指令：'L'（向左移动），'R'（向右移动），'U'（向上移动）和 'D'（向下移动）。
 * <p>
 * 机器人可以从 s 中的任一第 i 条指令开始执行。它将会逐条执行指令直到 s 的末尾，但在满足下述条件之一时，机器人将会停止：
 * <p>
 * 下一条指令将会导致机器人移动到网格外。
 * 没有指令可以执行。
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是机器人从第 i 条指令 开始 ，可以执行的 指令数目 。
 * <p>
 * 提示：
 * m == s.length
 * 1 <= n, m <= 500
 * startPos.length == 2
 * 0 <= startrow, startcol < n
 * s 由 'L'、'R'、'U' 和 'D' 组成
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no5964_execution_of_all_suffix_instructions_staying_in_a_grid {
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int len = s.length();
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = calNStep(n, startPos, s.substring(i));
        }

        return result;
    }

    private int calNStep(int n, int[] startPos, String path) {
        int stepCount = 0;

        int row = startPos[0];
        int col = startPos[1];
        for (int i = 0; i < path.length(); i++) {
            char ch = path.charAt(i);
            if (ch == 'L') {
                col = col - 1;
            } else if (ch == 'R') {
                col = col + 1;
            } else if (ch == 'U') {
                row = row - 1;
            } else if (ch == 'D') {
                row = row + 1;
            }
            if ((row >= 0) && (row < n) && (col >= 0) && (col < n)) {
                stepCount++;
            } else {
                break;
            }
        }
        return stepCount;
    }

    public static void main(String[] args) {
        String input = "HELLO";
        System.out.println(input.substring(0));
        System.out.println(input.substring(1));
        System.out.println(input.substring(2));
        System.out.println(input.substring(3));
        System.out.println(input.substring(4));

        no5964_execution_of_all_suffix_instructions_staying_in_a_grid obj = new no5964_execution_of_all_suffix_instructions_staying_in_a_grid();
        int n = 3;
        int[] startPos = new int[]{0,1};
        String s = "RRDDLU";
        int[] result = obj.executeInstructions(n,startPos,s);

        //输入：n = 3, startPos = [0,1], s = "RRDDLU"
        //输出：[1,5,4,3,1,0]
        System.out.println("n=" + n + ",startPos=" + JSON.toJSONString(startPos)+",s="+s+",result="+JSON.toJSONString(result));

        //输入：n = 2, startPos = [1,1], s = "LURD"
        //输出：[4,1,0,0]
        n = 2;
        startPos = new int[]{1,1};
        s = "LURD";
        result = obj.executeInstructions(n,startPos,s);
        System.out.println("n=" + n + ",startPos=" + JSON.toJSONString(startPos)+",s="+s+",result="+JSON.toJSONString(result));

        //输入：n = 1, startPos = [0,0], s = "LRUD"
        //输出：[0,0,0,0]
        n = 1;
        startPos = new int[]{0,0};
        s = "LRUD";
        result = obj.executeInstructions(n,startPos,s);
        System.out.println("n=" + n + ",startPos=" + JSON.toJSONString(startPos)+",s="+s+",result="+JSON.toJSONString(result));
    }
}
