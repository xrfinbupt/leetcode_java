package common;

/**
 * @author xurongfei
 * @Date 2021/10/30
 */
public class TrieNode {
    public TrieNode[] next = new TrieNode[26];
    public boolean isEnd = false;
    public TrieNode(){

    }
}
