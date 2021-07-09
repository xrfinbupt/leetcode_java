package Tree;

import common.NestedInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 341. 扁平化嵌套列表迭代器(Medium)
 *
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 *
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 *
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 *
 * 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
 */
public class no341_flatten_nested_list_iterator implements Iterator<Integer> {
    private List<Integer> result = new ArrayList<>();
    private int index = 0;

    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了6.86%的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了89.85%的用户
     *
     * 优化后（增加index，而不是删除result结果）
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了57.65%的用户
     */
    public no341_flatten_nested_list_iterator(List<NestedInteger> nestedList) {
        dfs(nestedList);
    }
    private void dfs(List<NestedInteger> nestedList){
        if(nestedList ==null) return;

        for(NestedInteger iter:nestedList){
            if(iter.isInteger()) result.add(iter.getInteger());
            else {
                if(iter.getList()!=null){
                    dfs(iter.getList());
                }else{
                    return;
                }
            }
        }
    }

    @Override
    public Integer next() {
        if(hasNext()) return result.get(index++);
        return null;
    }

    @Override
    public boolean hasNext() {
        if(result.size() <= index) return false;
        else return true;
    }
}
