package practise2023;

import java.util.Map;

public class LFUCache {

    // A B C
    // add item in cache
    // if no space remove least frequently used item
    // every item usage frequency is stored
    //

    public static void main(String[] args) {

    }

    static void add(Integer key, String val, Map<Integer, String> map) {

        String value = map.get(key);
        if(value != null) {
            ;
        }
        map.put(key, val);
    }
}