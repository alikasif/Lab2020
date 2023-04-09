package atlassian;

import java.util.ArrayList;
import java.util.List;

class ArrayElement {
    List<CacheNode> list = new ArrayList<>();

    void add(CacheNode node) {
        list.add(node);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    List<CacheNode> getList() {
        return list;
    }
}

public class KaalHashMap {

    public static void main(String[] args) {
        KaalHashMap khm = new KaalHashMap(5);
        khm.put(1, "kasif");
        khm.put(2, "asif");
        khm.display();
        khm.put(1, "fozia");
        khm.put(6, "six");
        khm.display();
        khm.put(11, "eleven");
        khm.put(17, "seventeen");
        khm.display();
        khm.put(1, "fozia");
        khm.put(6, "six");
        khm.display();
    }

    ArrayElement[] array = null;
    int capacity;
    public KaalHashMap(int capacity) {
        this.capacity = capacity;
        this.array = new ArrayElement[capacity];
    }

    void display() {

        for(ArrayElement e : array) {
            if(e != null) {
/*
                for (CacheNode c : e.getList()) {
                    System.out.println(c);
                }
*/
                System.out.println(e);
            }
        }
    }

    void put(Integer key, String value) {
        int index = key.hashCode() % capacity;
        ArrayElement arrayElement = array[index];
        if(arrayElement == null) {
            arrayElement = new ArrayElement();
            arrayElement.add(new CacheNode(key, value));
            array[index] = arrayElement;
        }
        else {
            for(CacheNode c : arrayElement.getList()) {
                if (c.key == key) {
                    c.value = value;
                    return;
                }
            }
            arrayElement.add(new CacheNode(key, value));
        }
    }
}
