package google2024.medium;

import java.util.*;

class tmp {

    Map<Character, Integer> map = new HashMap<>();
    public List<String> anagrams = new ArrayList<>();
    public tmp(String s) {
        for(int i=0; i<s.length(); i++) {
            Character c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        anagrams.add(s);
    }

    public void add(String s) {
        anagrams.add(s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        tmp tmp = (tmp) o;

        return Objects.equals(map, tmp.map);
    }

    @Override
    public int hashCode() {
        return map != null ? map.hashCode() : 0;
    }

    @Override
    public String toString() {
        return anagrams.toString();
    }
}

public class GroupAnagram {

    public static void main(String[] args) {
        String[] arr = {"eat","tea","tan","ate","nat","bat"};
        List<tmp> list = new ArrayList<>();
        for(String s : arr) {
            tmp t1 = new tmp(s);

            if(list.isEmpty()) {
                list.add(t1);
            }
            else {
                boolean found = false;
                for(tmp t2 : list) {
                    if(t1.equals(t2)) {
                        t2.add(s);
                        found= true;
                    }
                }
                if (!found) {
                    list.add(t1);
                }
            }
        }
        System.out.println(list);
    }

}
