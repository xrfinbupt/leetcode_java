package lcci;

/**
 * 面试题 05.08. 绘制直线
 *
 * 绘制直线。有个单色屏幕存储在一个一维数组中，使得32个连续像素可以存放在一个 int 里。屏幕宽度为w，且w可被32整除（即一个 int 不会分布在两行上），屏幕高度可由数组长度及屏幕宽度推算得出。请实现一个函数，绘制从点(x1, y)到点(x2, y)的水平线。
 * 给出数组的长度 length，宽度 w（以比特为单位）、直线开始位置 x1（比特为单位）、直线结束位置 x2（比特为单位）、直线所在行数 y。返回绘制过后的数组。
 *
 * 下面来自网友的翻译:
 * length 表示一共有多少个int，w/32表示一行有多少int，length/(w/32)表示一共有多少行，y表示我们对第几行操作。x1是起始点，x2是结束点。我们只需要把第y行的x1到x2的0置为1，把每个int的值按顺序加入数组。
 *
 * 示例1:
 *  输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
 *  输出：[3]
 *  说明：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011]
 *
 * 示例2:
 *  输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
 *  输出：[-1, -1, -1]
 *
 * 示例3：
 * 输入: length = 15, w = 96, x1 = 81, x2 =95, y = 1
 * 输出: [0,0,0,0,0,32767,0,0,0,0,0,0,0,0,0]
 *
 * 链接：https://leetcode-cn.com/problems/draw-line-lcci
 */
public class draw_line_lcci {
    /**
     * 这道题 描述 不够清楚
     *
     * @param length
     * @param w
     * @param x1
     * @param x2
     * @param y
     * @return
     */
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] result = new int[length];
        if(x1 > x2) {
            int temp = x2;
            x2 = x1;
            x1 = temp;
        }

        int len = w/32;
        int i = x1;
        int temp = 0;
        while(i<w){
            if (i > 0 && i % 32 == 0) {
                result[y * len + (i / 32 - 1)] = temp;
                temp = 0;
            }

            if (i >= x1 && i <= x2) {
                int ii = 31 - i % 32;
                temp = temp | (1 << ii);
            }
            if (i > x2) {
                result[y * len + (i/32)] = temp;
                break;
            }
            if (i == w - 1) {
                result[y * len + (i/32)] = temp;
            }
            i++;
        }

        return result;
    }

    /**
     * 参考网上解答
     *
     * @param length
     * @param w
     * @param x1
     * @param x2
     * @param y
     * @return
     */
    public int[] drawLine2(int length, int w, int x1, int x2, int y) {
        int []result = new int[length];
        long cnt = 0x80000000L;
        long temp = 0;
        int len = w/32;
        for(int i=0;i<w;i++){
            if(i>=x1 && i<=x2){
                temp = temp | cnt;
            }
            cnt = cnt >> 1;

            if(i>x2) {
                result[y * len + (i/32)] = (int)temp;
                break;
            }else if((i+1)%32==0){
                result[y * len + (i/32)] = (int)temp;
                temp = 0;
                cnt = 0x80000000L;
            }
        }
        return result;
    }
}
