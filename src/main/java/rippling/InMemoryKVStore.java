package rippling;

import java.util.*;

class NodeValue {

    NavigableMap<Integer, String> map = new TreeMap<>();

    public void set(String v, int t) {
        this.map.put(t, v);
    }

    public Map<Integer, String> get(int t) {
        return map.tailMap(t, true);
    }

    @Override
    public String toString() {
        return "NodeValue{" +
                "map = " + map +
                '}';
    }
}

class KVStore {

    Map<String, NodeValue> map = new HashMap<>();

    public void set(String key, String value, int t) {
        NodeValue nodeValue = map.get(key);
        if(nodeValue == null)
            nodeValue = new NodeValue();
        nodeValue.set(value, t);
        map.put(key, nodeValue);
    }

    public String get(String key, int t) {

        NodeValue nodeValue = map.get(key);

        if(nodeValue == null)
            return null;

        NavigableMap<Integer, String> timeMap = nodeValue.map;

        NavigableSet<Integer> keys = timeMap.navigableKeySet();

        if(keys.contains(t)) {
            return timeMap.get(t);
        }

        Integer integer = timeMap.floorKey(t);
        if(integer == null)
            return null;

        return timeMap.get(integer);
    }

    @Override
    public String toString() {
        return "KVStore : " + map;
    }
}

public class InMemoryKVStore {
    public static void main(String[] args) {
        timeBasedKVStore();

        NavigableMap<Integer, String> map = new TreeMap<>();
        map.put(1, "a");
        map.put(5, "b");
        map.put(3, "c");

        System.out.println(map.tailMap(1, true));
        System.out.println(map.tailMap(3, true));
        System.out.println(map.tailMap(5, true));
        System.out.println(map.tailMap(7, true));
        System.out.println(map.headMap(7, true));
        System.out.println(map.navigableKeySet());
    }

    static void timeBasedKVStore() {
        KVStore kvs = new KVStore();
        kvs.set("foo", "bar", 1);
        kvs.set("foo", "bar3", 5);
        kvs.set("foo", "bar2", 3);

        System.out.println(kvs.get("foo", 1));
        System.out.println(kvs.get("foo", 3));
        System.out.println(kvs.get("foo", 2));
        System.out.println(kvs.get("foo", 7));
        System.out.println(kvs.get("foo", 4));

    }
}