package atlassian;

import java.util.HashMap;
import java.util.Map;

class CacheNode {
    int key;
    String value;

    public CacheNode(int x, String s) {
        this.key = x;
        this.value = s;
    }

    @Override
    public String toString() {
        return "(" + this.key +"."+ this.value +")";
    }
}

class DLLNode {
    CacheNode value;
    DLLNode prev;
    DLLNode next;

    public DLLNode(CacheNode x) {
        this.value = new CacheNode(x.key, x.value);
    }

    @Override
    public String toString() {
        return "DLLNode{" +
                "value=" + value.value +
                ", prev=" + (prev == null? null : prev.value) +
                ", next=" + (next == null? null : next.value) +
                '}';
    }
}

class DoublyLinkListQueue {

    DLLNode head = null;
    DLLNode last = null;
    int size=0;

    DLLNode add(CacheNode cacheNode) {
        DLLNode tmp = new DLLNode(cacheNode);
        if( head == null) {
            head = tmp;
            last = tmp;
        }
        else {
            tmp.next = head;
            head.prev = tmp;
            head = tmp;
        }
        size++;
        return tmp;
    }

    void moveToFront(DLLNode node) {
        if (last.value.key == node.value.key) {
            last = node.prev;
        }
        DLLNode p = node.prev;
        DLLNode n = node.next;

        if(p != null)
            p.next = n;
        if (n != null)
            n.prev = p;

        DLLNode c = head;

        node.next = c;
        node.prev = null;
        c.prev = node;
        head = node;
    }

    void remove(DLLNode node) {

        if(head == null)
            return;

        if(node.prev == null) {
         node.next.prev = null;
         head = node.next;
         node.prev = null;
         node.next = null;
        }
        else {
            last = node.prev;
            node.prev.next = node.next;
            if(node.next != null)
                node.next.prev = node.prev;
        }
        size--;
    }

    void removeLRUNode() {
        System.out.println(last);
        last = last.prev;
        last.next.prev = null;
        last.next = null;
    }

    void display(){
        DLLNode tmp = head;
        while (tmp != null) {
            System.out.print( tmp +" => " );
            tmp = tmp.next;
        }
        System.out.println();
    }
}

public class LRUCache {

    Map<Integer, DLLNode> map = new HashMap<>();
    DoublyLinkListQueue dlq = new DoublyLinkListQueue();

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache();
        lruCache.add(1, "Kasif");
        lruCache.add(2, "asif");
        lruCache.add(3, "fozia");
        lruCache.add(4, "Aizah");
        lruCache.display();
        lruCache.get(1);
        lruCache.display();

    }

    void display() {
        dlq.display();
    }

    void testDLQ() {

        DLLNode kasif = dlq.add(new CacheNode(1, "kasif"));
        DLLNode asif = dlq.add(new CacheNode(2, "asif"));
        dlq.add(new CacheNode(3, "fozia"));
        DLLNode aizah = dlq.add(new CacheNode(4, "aizah"));

        dlq.display();
        dlq.moveToFront(asif);
        dlq.display();
        dlq.remove(kasif);
        dlq.display();
        dlq.removeLRUNode();
        dlq.display();
    }

    void add(int key, String value) {
        if(map.containsKey(key)) {
            DLLNode dllNode = map.get(key);
            dllNode.value.value = value;
            dlq.moveToFront(dllNode);
        }
        else {
            if (dlq.size < 5) {
                DLLNode add = dlq.add(new CacheNode(key, value));
                map.put(key, add);
            }
            else {
                dlq.removeLRUNode();
                DLLNode add = dlq.add(new CacheNode(key, value));
                map.put(key, add);
            }
        }
    }

    String get(int key) {
        if(map.containsKey(key)) {
            DLLNode dllNode = map.get(key);
            dlq.moveToFront(dllNode);
            return dllNode.value.value;
        }
        else {
            return null;
        }
    }
}
