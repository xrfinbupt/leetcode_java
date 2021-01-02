package high_frequency.easy;

import java.util.LinkedList;

// https://leetcode-cn.com/problems/valid-parentheses/description
public class no6_valid_parentheses {
    public boolean isValid(String s) {
        LinkedList stack = new LinkedList<Character>();

        int len = s.length();
        for(int i=0;i<len;i++){
            char ch = s.charAt(i);
            if(ch == ')'){
                if(stack.size()<=0) return false;

                Character character = (Character)stack.pop();
                if(character.charValue()!='(') return false;
            }else if(ch == '}'){
                if(stack.size()<=0) return false;

                Character character = (Character)stack.pop();
                if(character.charValue()!='{') return false;
            }
            else if(ch == ']'){
                if(stack.size()<=0) return false;

                Character character = (Character)stack.pop();
                if(character.charValue()!='[') return false;
            }else{
                stack.push(ch);
            }
        }
        if(stack.size()>0) return false;
        return true;
    }
}
