package common;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {
    private Integer integer;
    private List<NestedInteger> list;

    public NestedInteger(){
        integer = null;
        list = new ArrayList<>();
    }
    public NestedInteger(int value){
        integer = value;
        list = new ArrayList<>();
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger(){
        if(integer!=null) return true;

        return false;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger(){
        return integer;
    }

    public void setInteger(int value){
        integer = value;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList(){
        return list;
    }

    public void add(NestedInteger ni){
        if (integer != null) {
            NestedInteger pre = new NestedInteger();
            pre.setInteger(integer);
            list.add(pre);
            integer = null;
        }
        list.add(ni);
        return;
    }
}

