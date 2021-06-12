package lcci;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 面试题 17.22. 单词转换
 *
 * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 *
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 *
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出:
 * ["hit","hot","dot","lot","log","cog"]
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: []
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 *
 * 链接：https://leetcode-cn.com/problems/word-transformer-lcci
 */
public class word_transformer_lcci {
    List<String> result = null;

    /**
     * 暴力法 超时了
     */
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        result = new ArrayList<>();
        if (!wordList.contains(endWord)) return result;
        int len = wordList.size();

        Set<String> pathSet = new HashSet<>();
        pathSet.add(beginWord);

        List<String> paths = new ArrayList<>();
        paths.add(beginWord);

        sub_findLadders(1,len,pathSet,paths,endWord,wordList);
        return result;
    }

    public boolean sub_findLadders(int level, int len, Set<String> pathSet, List<String> paths, String endWord, List<String> wordList) {
        if (level >= len) return false;

        if (pathSet.contains(endWord)) {
            result = paths;
            return true;
        }

        if (oneCharDiff(endWord, paths.get(paths.size() - 1))) {
            paths.add(endWord);
            result = paths;
            return true;
        }
        for (String iter : wordList) {
            if (result.size() > 0) {
                return true;
            }
            if (pathSet.contains(iter)) continue;
            if (oneCharDiff(iter, paths.get(paths.size() - 1))) {
                pathSet.add(iter);
                paths.add(iter);
                if(sub_findLadders(level + 1, len, pathSet, paths, endWord, wordList)){
                    return true;
                }else{
                    paths.remove(paths.size()-1);
                }
            }
        }
        return false;
    }

    /**
     * 参考 https://leetcode-cn.com/problems/word-transformer-lcci/solution/zui-rong-yi-li-jie-de-hui-su-dai-ma-by-momentumxx/
     *
     */
    public List<String> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<String> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return ans;
        }
        Set<String> used = new HashSet<>();
        ans.add(beginWord);
        boolean b = backTrack(1,beginWord, endWord, wordList, ans, used);
        return b ? ans : new ArrayList<>();
    }

    private boolean backTrack(int level,String beginWord, String endWord, List<String> wordList, List<String> ans, Set<String> used) {
        if (beginWord.equals(endWord)) {
            return true;
        }
        if(level>wordList.size()) {
            return false;
        }

        for (String word : wordList) {
            if (!used.contains(word) && oneCharDiff(beginWord, word)) {
                ans.add(word);
                used.add(word);
                if (backTrack(level+1,word, endWord, wordList, ans, used)) {
                    return true;
                } else {
                    //如果本次递归没能得到结果,那么word也不应该再用了
                    //used.remove(word);
                    ans.remove(ans.size() - 1);
                }
            }
        }
        return false;
    }

    private boolean oneCharDiff(String word1, String word2) {
        int count = 0;
        if (word1.length() != word2.length()) {
            return false;
        }
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
                if (count >= 2) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    public static void main(String args[]){
        word_transformer_lcci obj = new word_transformer_lcci();
        List<String> result = obj.findLadders2("hit","cog", Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println("\"hit\",\"cog\"="+JSON.toJSON(result));

        result = obj.findLadders2("hit","cog", Arrays.asList("hot","dot","dog","lot","log"));
        System.out.println("\"hit\",\"cog\"="+JSON.toJSON(result));

        result = obj.findLadders2("qa","sq", Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"));
        System.out.println("\"qa\",\"sq\"="+JSON.toJSON(result));


        result = obj.findLadders("cet","ism", Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"));
        System.out.println("\"cet\",\"ism\"="+JSON.toJSON(result));
    }
}
