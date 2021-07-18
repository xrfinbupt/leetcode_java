package DFS_BFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 *
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 * 示例：
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 *
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *  
 *
 * 提示：
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 50000 次 addWord 和 search
 *
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 */
public class no211_design_add_and_search_words_data_structure {
    Set<String> datas = null;
    Map<String,Boolean> result = null;

    TierTree tree = null;
    boolean searchResultFlag = false;
    /**
     * 执行用时：53 ms, 在所有 Java 提交中击败了65.16%的用户
     * 内存消耗：50.1 MB, 在所有 Java 提交中击败了5.05%的用户
     */
    /** Initialize your data structure here. */
    public no211_design_add_and_search_words_data_structure() {
        // method1
        //datas = new HashSet<>();
        //result = new HashMap<>();

        // method2
        tree = new TierTree();
    }

    public void addWord(String word) {
        if(word==null) return;

        // method1
        //datas.add(word);
        //result.clear();

        // method2
        dfs(word.toCharArray(),0,tree);
    }

    public boolean search(String word) {
        if(datas.contains(word)) return true;
        if(!word.contains(".")) return false;
        if(result.containsKey(word)){
            return result.get(word);
        }

        for (String input : datas) {
            if (compare(input, word)) {
                result.put(word, true);
                return true;
            }
        }
        result.put(word,false);
        return false;
    }

    /**
     * 执行用时：56 ms, 在所有 Java 提交中击败了51.76%的用户
     * 内存消耗：49.1 MB, 在所有 Java 提交中击败了49.23%的用户
     */
    public boolean search2(String word) {
        searchResultFlag = false;
        char []wordArray= word.toCharArray();

        dfsSearch(wordArray,0,tree);

        return searchResultFlag;
    }
    private void dfsSearch(char[] wordArray,int i,TierTree tree){
        if (i >= wordArray.length || searchResultFlag || tree == null) {
            return;
        }
        char ch = wordArray[i];
        if(ch == '.'){
            for(Character key:tree.dataMap.keySet()){
                dfsSearchcommon(wordArray,i,key,tree);
            }
        }else{
            dfsSearchcommon(wordArray,i,ch,tree);
        }

        return ;
    }
    private void dfsSearchcommon(char[] wordArray,int i,char key,TierTree dataTree){
        dataTree = dataTree.dataMap.get(key);
        if(dataTree == null){
            return;
        }else if(dataTree.endFlag && wordArray.length == i+1){
            searchResultFlag = true;
            return;
        }else if(wordArray.length == i+1){
            return;
        }else{
            dfsSearch(wordArray,i+1,dataTree);
        }
    }
    public boolean compare(String input,String word) {
        if(input.length()!=word.length()) return false;

        int i = 0;
        for (char ch : word.toCharArray()) {
            if (ch != '.' && input.charAt(i) != ch) {
                return false;
            }
            i++;
        }
        return true;
    }
    private void dfs(char[] wordArray,int i,TierTree tree){
        if (i >= wordArray.length) {
            tree.endFlag = true;
            return;
        }

        char firstChar = wordArray[i];
        TierTree data = tree.dataMap.getOrDefault(firstChar,new TierTree());
        tree.dataMap.put(firstChar,data);
        dfs(wordArray,i+1,data);
    }
    static class TierTree {
        public boolean endFlag = false;
        public Map<Character, TierTree> dataMap = new HashMap<>();
    }

    public static void main(String args[]){
        no211_design_add_and_search_words_data_structure obj = new no211_design_add_and_search_words_data_structure();
        obj.addWord("a");
        System.out.println(obj.search2("ab"));
        System.out.println(obj.search2("a"));
        System.out.println(obj.search2("."));
        obj.addWord("ab");
        System.out.println();
        System.out.println(obj.search2(".b"));
        System.out.println(obj.search2("a."));
        System.out.println(obj.search2("ac"));
    }
}
