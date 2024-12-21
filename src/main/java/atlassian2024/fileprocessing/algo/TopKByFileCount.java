package atlassian2024.fileprocessing.algo;

import atlassian2024.fileprocessing.dto.FileCollection;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class  TopKByFileCount implements ITopKStrategy{


    @Override
    public List<FileCollection> getTopKCollections(int k, Map<String, FileCollection> nameToFileCollectionMap) {
        List<FileCollection> sortedFileCollections = nameToFileCollectionMap.values().stream().sorted(new Comparator<FileCollection>() {
            @Override
            public int compare(FileCollection fc1, FileCollection fc2) {
                return fc1.getFileCount().equals(fc2.getFileCount()) ?
                        fc2.getName().compareTo(fc1.getName()) :
                        fc2.getFileCount().compareTo(fc1.getFileCount());
            }
        }).toList();

        if (sortedFileCollections.isEmpty())
            return sortedFileCollections;

        k = Math.min(k, sortedFileCollections.size());

        return sortedFileCollections.subList(0, k);
        //return sortedFileCollections;
    }
}
