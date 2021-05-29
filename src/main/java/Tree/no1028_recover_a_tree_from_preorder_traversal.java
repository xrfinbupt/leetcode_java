package Tree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1028. 从先序遍历还原二叉树
 *
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 *  
 *
 * 示例 1：
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 *
 * 示例 2：
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 *
 * 示例 3：
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 *
 * 提示：
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 *
 * 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 */
public class no1028_recover_a_tree_from_preorder_traversal {
    public TreeNode recoverFromPreorder(String traversal) {
        if(!traversal.contains("-")){
            return new TreeNode(Integer.valueOf(traversal));
        }

        int len = traversal.length();
        int pos = 0;
        TreeNode root = null,p=null;
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        int deep = 0;
        while (true) {
            int deep2 = 0;
            if (traversal.charAt(pos) != '-') {
                int pos1 = traversal.indexOf("-");
                root = new TreeNode(Integer.valueOf(traversal.substring(0, pos1)));
                p = root;
                stack.addFirst(p);
                pos = pos1;
            } else {
                Object[] objs = getDeep(pos, traversal,len);
                if(objs==null) break;

                deep2 = (int)objs[0];
                String str = (String)objs[1];
                pos = (int)objs[2];
                TreeNode curr = new TreeNode(Integer.valueOf(str));

                if(deep2==deep+1){
                    p.left = curr;
                }else if(deep2 == deep){
                    stack.removeFirst();
                    p = stack.peekFirst();
                    p.right = curr;
                }else {// deep2 < deep
                    stack.removeFirst();

                    int diff = deep - deep2;
                    while(diff-->0){
                        stack.removeFirst();
                    }
                    p = stack.peekFirst();
                    p.right = curr;
                }

                p = curr;
                stack.addFirst(curr);
            }

            deep = deep2;
            if (pos >= len) break;
        }

        return root;
    }
    public Object[] getDeep(int pos,String traversal,int len){
        if(pos >= len) return null;

        int deep = 0;
        while(traversal.charAt(pos)=='-'){
            deep++;
            pos++;
        }
        int i = pos;
        while(pos<len && traversal.charAt(pos)!='-') pos++;
        String str = traversal.substring(i,pos);
        return new Object[]{deep,str,pos};
    }



    public static void main(String args[]) {
        Deque<String> stack = new ArrayDeque<String>();
        stack.addFirst("1");
        stack.addFirst("2");
        System.out.println(stack.toString());


        stack.addFirst("3");
        System.out.println(stack.peekFirst());
        System.out.println(stack.toString());

        stack.removeFirst();
        System.out.println(stack.toString());

        no1028_recover_a_tree_from_preorder_traversal obj = new no1028_recover_a_tree_from_preorder_traversal();
        obj.recoverFromPreorder("1-2--3--4-5--6--7");
        obj.recoverFromPreorder("1-2--3---4-5--6---7");
        obj.recoverFromPreorder("1-401--349---90--88");
    }
}
