package atlassian;

import java.util.*;
import java.util.stream.Collectors;

class File  {

    int size;
    String fileName;

    public File(String name, int size) {
        this.fileName = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return fileName+"|"+size;
    }
}

class FileCollection implements Comparable<FileCollection>{

    String collectionName;
    List<File> files = new ArrayList<>();
    int totalSize;

    public FileCollection(String n) {
        collectionName = n;
    }
    public void add(File f) {
        totalSize += f.size;
        files.add(f);
    }

    @Override
    public int compareTo(FileCollection o) {
        return o.totalSize - this.totalSize;
    }

    @Override
    public String toString() {
        return collectionName+"|"+totalSize;
    }
}

public class MaxFileSizeCollection {
    public static void main(String[] args) {

        Map<String, FileCollection> map = new HashMap<>();

        FileCollection fileCollection1 = new FileCollection("coll1");
        fileCollection1.add(new File("f1", 100));
        fileCollection1.add(new File("f2", 200));

        FileCollection fileCollection2 = new FileCollection("coll2");
        fileCollection2.add(new File("f1", 100));
        fileCollection2.add(new File("f3", 200));

        FileCollection fileCollection3 = new FileCollection(null);
        fileCollection3.add(new File("f4", 700));
        fileCollection3.add(new File("f3", 200));

        List<FileCollection> fileCollections = new ArrayList<>();
        fileCollections.add(fileCollection1);
        fileCollections.add(fileCollection2);
        fileCollections.add(fileCollection3);

        Collections.sort(fileCollections, Comparator.reverseOrder());
        System.out.println(fileCollections);

/*
        List<Map.Entry<String, Integer>> list = fileCollection.map.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toList());
        System.out.println(list);
        */
    }
}