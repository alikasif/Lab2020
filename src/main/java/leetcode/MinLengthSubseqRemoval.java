package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinLengthSubseqRemoval {

    public static void main(String[] args) {

        //String s = "[]])([";
        String s = "([)(])";

        Map<String, List<Integer>> map = new HashMap<>();

        for(int i =0; i< s.length(); i++) {
            String t = String.valueOf(s.charAt(i));
            if ("(".equals(t) || "[".equals(t)) {
                List<Integer> list = map.getOrDefault(t, new ArrayList<>());
                list.add(i);
                map.put(t,list);
            }
            else {
                if (")".equals(t)) {
                    List<Integer> list = map.getOrDefault("(", new ArrayList<>());
                    Integer y = -1;
                    for(Integer x : list) {
                        if (x < i) {
                            y = x;
                            break;
                        }
                    }
                    if( y != -1)
                        list.remove(y);
                    else {
                        List<Integer> tmp = map.getOrDefault(t, new ArrayList<>());
                        tmp.add(i);
                        map.put(t,tmp);
                    }
                }
                if ("]".equals(t)) {
                    List<Integer> list = map.getOrDefault("[", new ArrayList<>());
                    Integer y = -1;
                    for(Integer x : list) {
                        if (x < i){
                            y = x;
                            break;
                        }
                    }
                    if (y != -1)
                        list.remove(y);
                    else {
                        List<Integer> tmp = map.getOrDefault(t, new ArrayList<>());
                        tmp.add(i);
                        map.put(t,tmp);
                    }
                }
            }
        }
        System.out.println(map);
    }
}
