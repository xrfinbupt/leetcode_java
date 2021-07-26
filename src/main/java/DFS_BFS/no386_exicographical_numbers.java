package DFS_BFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 386. 字典序排数
 *
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 *
 * 例如，
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 *
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 *
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
 */
public class no386_exicographical_numbers {
    private Tree dataTree;
    private List<Integer> result;

    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了18.35%的用户
     * 内存消耗：44.7 MB, 在所有 Java 提交中击败了11.39%的用户
     */
    public List<Integer> lexicalOrder(int n) {
        result = new ArrayList<>();
        if(n<=0) return new ArrayList<>();
        if(n==1) {
            result.add(n);
            return result;
        }
        dataTree = new Tree();
        for(int i=1;i<=n;i++){
            dfs(i+"",0,dataTree);
        }
        printDfs(dataTree,0);

        return result;
    }
    private void dfs(String indexStr,int i,Tree preTree){
        if(indexStr.length() <= i) return;

        if(preTree.next==null){
            preTree.next = new Tree[10];
        }

        int currInt = new Integer(indexStr.charAt(i)-'0');
        if(preTree.next[currInt]==null){
            preTree.next[currInt] = new Tree();
        }
        Tree currTree = preTree.next[currInt];
        if(currTree.data==null){
            currTree.data = currInt;
        }

        dfs(indexStr,i+1,currTree);
    }
    private void printDfs(Tree iter,int preNum){
        int nextNum = 0;
        if(iter.data!=null){
            nextNum = preNum*10+iter.data;
            result.add(preNum*10+iter.data);
        }
        if(iter.next==null) return;
        for(int i=0;i<10;i++){
            Tree nextIter = iter.next[i];
            if(nextIter!=null) printDfs(nextIter,nextNum);
        }
    }
    static class Tree{
        public Integer data;
        /**
         * 0 ~ 9
         */
        public Tree[] next;

        public Tree(){}
    }
    public static void main(String args[]){
        no386_exicographical_numbers obj = new no386_exicographical_numbers();
        System.out.println(obj.lexicalOrder(103));
    }
}
