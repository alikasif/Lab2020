package atlassian;

import java.util.*;
import java.util.stream.Collectors;

class File implements Comparable<File> {

    String collectionName;
    int size;

    public File(String name, int size) {
        this.collectionName = name;
        this.size = size;
    }

    @Override
    public int compareTo(File o) {
        return o.size - this.size;
    }

    @Override
    public String toString() {
        return collectionName+"|"+size;
    }
}

class FileCollection {
    Map<String, Integer> map = new HashMap<>();

    public void add(File f) {
        Integer size = map.get(f.collectionName);
        if(size == null)
            map.put(f.collectionName, f.size);
        else {
            map.put(f.collectionName, size+f.size);
        }
    }
}

public class MaxFileSizeCollection {
    public static void main(String[] args) {

        FileCollection fileCollection = new FileCollection();
        fileCollection.add(new File("coll1", 100));
        fileCollection.add(new File("coll2", 200));
        fileCollection.add(new File("coll1", 100));
        fileCollection.add(new File("coll2", 200));

        List<Map.Entry<String, Integer>> list = fileCollection.map.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                collect(Collectors.toList());

        System.out.println(list);
    }
}
