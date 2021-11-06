package Other;

import java.util.HashMap;

/**
 * 146. LRU 缓存机制
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *  
 * <p>
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 10^5
 * 最多调用 2 * 10^5 次 get 和 put
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class no146_lru_cache {
    HashMap<Integer, CacheNode> dataMap = new HashMap<>();
    int capacity = 0, size = 0;
    CacheNode head, tail;

    /**
     * 执行用时：43 ms, 在所有 Java 提交中击败了94.09%的用户
     * 内存消耗：106.8 MB, 在所有 Java 提交中击败了93.31%的用户
     */
    public no146_lru_cache(int capacity) {
        dataMap = new HashMap<>(capacity);
        this.capacity = capacity;
        size = 0;
    }

    public int get(int key) {
        CacheNode dataNode = dataMap.get(key);
        if (dataNode == null) {
            return -1;
        }
        sort(dataNode, null);

        return dataNode.value;
    }

    public void put(int key, int value) {
        CacheNode dataNode = null;
        CacheNode existNode = dataMap.get(key);
        if (existNode == null) {
            dataNode = new CacheNode(key, value);
            dataMap.put(key, dataNode);
        } else {
            existNode.value = value;
        }
        sort(existNode, dataNode);
    }

    /**
     * 这一版代码清晰很多 之前的一版bug好多 而且代码写的狗屎一样
     */
    private void sort(CacheNode existNode, CacheNode dataNode) {
        if (existNode == null && dataNode == null) return;
        if (existNode != null) {
            //3 case: head middle tail
            if (existNode.pre == null) {
                return;
            } else if (existNode.next == null) {
                tail = existNode.pre;
            }//else

            CacheNode pre = existNode.pre;
            CacheNode next = existNode.next;
            if (pre != null) {
                pre.next = next;
            }
            if (next != null) {
                next.pre = pre;
            }

            existNode.pre = null;
            existNode.next = head;
            head.pre = existNode;
            head = existNode;
        } else if (dataNode != null) {
            size++;
            //3 case: empty notFull full
            if (tail == null) {
                tail = dataNode;
            } else {
                dataNode.next = head;
                head.pre = dataNode;
            }
            head = dataNode;

            if (size > capacity) {
                int rmKey = tail.key;
                CacheNode pre = tail.pre;
                if (pre != null) {
                    pre.next = null;
                    tail = pre;
                } else {
                    tail = dataNode;
                }

                dataMap.remove(rmKey);
                size = capacity;
            }
        }
    }

    public static void main(String[] args) {
        no146_lru_cache lRUCache = new no146_lru_cache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int val = lRUCache.get(1);    // 返回 1
        System.out.println("get(1) should return 1,==" + val);
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        val = lRUCache.get(2);    // 返回 -1 (未找到)
        System.out.println("get(2) should return -1,==" + val);
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        val = lRUCache.get(1);    // 返回 -1 (未找到)
        System.out.println("get(1) should return -1,==" + val);
        val = lRUCache.get(3);    // 返回 3
        System.out.println("get(3) should return 3,==" + val);
        val = lRUCache.get(4);    // 返回 4
        System.out.println("get(4) should return 4,==" + val);
        System.out.println();


        //["LRUCache","put","put","get","put","get","put","get","get","get"]
        //[[2],[1,0],[2,2],       [1], [3,3],  [2],[4,4],[1],[3],[4]]
        lRUCache = new no146_lru_cache(2);
        lRUCache.put(1, 0); // 缓存是 {1=0}
        lRUCache.put(2, 2); // 缓存是 {1=0, 2=2}
        val = lRUCache.get(1);    // 返回 0
        System.out.println("get(1) should return 0,==" + val);
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=0, 3=3}
        val = lRUCache.get(2);    // 返回 -1 (未找到)
        System.out.println("get(2) should return -1,==" + val);
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        val = lRUCache.get(1);    // 返回 -1 (未找到)
        System.out.println("get(1) should return -1,==" + val);
        val = lRUCache.get(3);    // 返回 3
        System.out.println("get(3) should return 3,==" + val);
        val = lRUCache.get(4);    // 返回 4
        System.out.println("get(4) should return 4,==" + val);
        System.out.println();

        //["LRUCache","put","get","put","get","get"]
        //[[1],[2,1],[2],[3,2],[2],[3]]
        lRUCache = new no146_lru_cache(1);
        lRUCache.put(2, 1); // 缓存是 {2=1}
        val = lRUCache.get(2);    // 返回 -1
        System.out.println("get(2) should return 1,==" + val);
        lRUCache.put(3, 2); // 该操作会使得关键字 2 作废，缓存是 {3=2}
        val = lRUCache.get(2);    // 返回 -1 (未找到)
        System.out.println("get(2) should return -1,==" + val);
        val = lRUCache.get(3);    // 返回 2
        System.out.println("get(3) should return 2,==" + val);
        System.out.println();

        //["LRUCache","put","put","put","put","get","get"]
        //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        lRUCache = new no146_lru_cache(2);
        lRUCache.put(2, 1);
        lRUCache.put(1, 1);
        lRUCache.put(2, 3);
        lRUCache.put(4, 1);
        val = lRUCache.get(1);
        System.out.println("get(1) should return -1 ==" + val);
        val = lRUCache.get(2);
        System.out.println("get(2) should return 3 ==" + val);
        System.out.println();

        //["LRUCache","put","put","get","get","put","get","get","get"]
        //[[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
        lRUCache = new no146_lru_cache(2);
        lRUCache.put(2, 1);
        lRUCache.put(3, 2);
        val = lRUCache.get(3);
        System.out.println("get(3) should return 2 ==" + val);
        val = lRUCache.get(2);
        System.out.println("get(2) should return 1 ==" + val);
        lRUCache.put(4, 3);
        val = lRUCache.get(2);
        System.out.println("get(2) should return 1 ==" + val);
        val = lRUCache.get(3);
        System.out.println("get(3) should return -1 ==" + val);
        val = lRUCache.get(4);
        System.out.println("get(4) should return 3 ==" + val);
    }

    static class CacheNode {
        public CacheNode pre, next;
        public int key;
        public int value;

        public CacheNode() {
        }

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

