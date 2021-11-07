package Trie;

/**
 * 208. 实现 Trie (前缀树)
 * <p>
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *  
 * 提示：
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 *
 * @author xurongfei
 * @Date 2021/11/7
 */
public class no208_implement_trie_prefix_tree {
    TrieNode root = new TrieNode();

    /**
     * 执行用时：37 ms, 在所有 Java 提交中击败了34.03%的用户
     * 内存消耗：49.1 MB, 在所有 Java 提交中击败了26.03%的用户
     */
    public no208_implement_trie_prefix_tree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode p = root;
        for (char ch : word.toCharArray()) {
            int key = ch - 'a';
            TrieNode data = p.data[key];
            if (data == null) {
                p.data[key] = new TrieNode();
            }
            p = p.data[key];
        }
        p.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode p = root;
        for (char ch : word.toCharArray()) {
            int key = ch - 'a';
            TrieNode data = p.data[key];
            if (data == null) {
                return false;
            }
            p = p.data[key];
        }
        if (p.isEnd) return true;
        return false;
    }

    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (char ch : prefix.toCharArray()) {
            int key = ch - 'a';
            TrieNode data = p.data[key];
            if (data == null) {
                return false;
            }
            p = p.data[key];
        }
        return true;
    }

    static class TrieNode {
        public TrieNode[] data = new TrieNode[26];
        public boolean isEnd = false;

        public TrieNode() {
        }
    }
}
