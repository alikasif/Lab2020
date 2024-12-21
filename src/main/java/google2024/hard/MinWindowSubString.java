package google2024.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class IndexNode implements Comparable<IndexNode> {

    int i;
    String s;

    public IndexNode(int i1, String s1){
        i =i1;
        s=s1;
    }

    @Override
    public int compareTo(IndexNode o) {
        return Integer.compare(this.i, o.i);
    }

    @Override
    public String toString() {
        return "IndexNode{" +
                "i=" + i +
                ", s='" + s + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexNode indexNode = (IndexNode) o;

        return Objects.equals(s, indexNode.s);
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }
}

public class MinWindowSubString {

    public static void main(String[] args) {

        String s = "ADOBECODEBANC";

        String t = "ABC";

        List<IndexNode> list = new ArrayList<>();

        for(int i=0; i<s.length(); i++) {
            String x = String.valueOf(s.charAt(i));
            if (t.contains(x)) {
                list.add(new IndexNode(i, x));
            }
        }

        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        List<IndexNode> window = new ArrayList<>();

        for(int i=0; i< list.size(); i++) {

            IndexNode indexNode = list.get(i);
            int index = window.indexOf(indexNode);
            window.add(indexNode);

            for(int k=0; k<=index; k++){
                window.remove(0);
            }

            if(window.size() == t.length()) {
                System.out.println(window.get(0) + " | " + window.get(window.size()-1));
            }
        }

    }
}

