package DFS_BFS;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * 365. 水壶问题
 *
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 *
 * 示例 1: (From the famous "Die Hard" example)
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 *
 * 示例 2:
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 *
 * 链接：https://leetcode-cn.com/problems/water-and-jug-problem
 */
public class no365_water_and_jug_problem {
    ArrayDeque<int[]> queue = new ArrayDeque<>();
    Set<Long> visited = new HashSet<>();

    /**
     * 参考https://leetcode-cn.com/problems/water-and-jug-problem/solution/shui-hu-wen-ti-by-leetcode-solution/
     *
     * 执行用时：441 ms, 在所有 Java 提交中击败了30.16%的用户
     * 内存消耗：97.7 MB, 在所有 Java 提交中击败了26.26%的用户
     */
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        queue = new ArrayDeque<>();
        visited = new HashSet<>();

        if(targetCapacity > jug1Capacity+jug2Capacity) return false;
        queue.addLast(new int[]{0,0});

        while(!queue.isEmpty()){
            long xy = hash(queue.peekFirst());
            if(visited.contains(xy)){
                queue.removeFirst();
                continue;
            }
            visited.add(xy);
            int[] data = queue.removeFirst();
            int xTake = data[0],yTake = data[1];
            if (targetCapacity == xTake || targetCapacity == yTake || targetCapacity == xTake + yTake) {
                return  true;
            }
            //装满任意一个水壶
            queue.addLast(new int[]{jug1Capacity,yTake});
            queue.addLast(new int[]{xTake,jug2Capacity});

            //清空任意一个水壶
            queue.addLast(new int[]{0,yTake});
            queue.addLast(new int[]{xTake,0});

            //从一个水壶向另外一个水壶倒水，直到装满或者倒空
            // x => y
            queue.addLast(new int[]{xTake - Math.min(xTake,jug2Capacity-yTake),yTake+Math.min(xTake,jug2Capacity-yTake)});
            // y => x
            queue.addLast(new int[]{xTake + Math.min(yTake,jug1Capacity-xTake),yTake-Math.min(yTake,jug1Capacity-xTake)});
        }

        return false;
    }

    private long hash(int[] pos){
        return (long)pos[0] * 1000000 + pos[1];
    }
//
//    private void dfs(int value, int xCapacity, int yCapacity, int xTake, int yTake) {
//        if (value == xTake || value == yTake || value == xTake + yTake) {
//            result = true;
//        }
//        if (result) return;
//
//        //装满任意一个水壶
//        //清空任意一个水壶
//        //从一个水壶向另外一个水壶倒水，直到装满或者倒空
//        if (xCapacity != xTake)
//            dfs(value, xCapacity, yCapacity, xCapacity, yTake);
//        if (yCapacity != yTake)
//            dfs(value, xCapacity, yCapacity, xTake, yCapacity);
//
//        /*if (xTake != 0)
//            dfs(value, xCapacity, yCapacity, 0, yTake);
//        if (yTake != 0)
//            dfs(value, xCapacity, yCapacity, xTake, 0);
//*/
//        // x => y
//        if (xTake != 0 && yTake != yCapacity) {
//            int targetY = (xTake + yTake) > yCapacity ? yCapacity : (xTake + yTake);
//            int targetX = (xTake + yTake) > yCapacity ? (xTake + yTake) % yCapacity : 0;
//            dfs(value, xCapacity, yCapacity, targetX, targetY);
//        }
//        // y => x
//        if (yTake != 0 && xTake != xCapacity) {
//            int targetY = (xTake + yTake) > xCapacity ? (xTake + yTake - xCapacity) : 0;
//            int targetX = (xTake + yTake) > xCapacity ? xCapacity : (xTake + yTake);
//            dfs(value, xCapacity, yCapacity, targetX, targetY);
//        }
//    }

    public static void main(String args[]){
        no365_water_and_jug_problem obj = new no365_water_and_jug_problem();
        System.out.println("3,5,4="+obj.canMeasureWater(3,5,4));
        System.out.println("3,5,2="+obj.canMeasureWater(3,5,2));
        System.out.println("3,5,3="+obj.canMeasureWater(3,5,3));
        System.out.println("3,5,5="+obj.canMeasureWater(3,5,5));
        System.out.println("3,5,6="+obj.canMeasureWater(3,5,6));
        System.out.println("3,5,7="+obj.canMeasureWater(3,5,7));
        System.out.println("3,5,8="+obj.canMeasureWater(3,5,8));
        System.out.println("3,5,9="+obj.canMeasureWater(3,5,9));
    }
}
