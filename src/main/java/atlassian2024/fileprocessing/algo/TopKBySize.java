package atlassian2024.fileprocessing.algo;

import atlassian2024.fileprocessing.dto.FileCollection;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TopKBySize implements ITopKStrategy{

    public TopKBySize(){
    }

    @Override
    public List<FileCollection> getTopKCollections(int k, Map<String, FileCollection> nameToFileCollectionMap) {
        List<FileCollection> sortedFileCollections = nameToFileCollectionMap.values().stream().sorted(new Comparator<FileCollection>() {
            @Override
            public int compare(FileCollection fc1, FileCollection fc2) {
                return fc1.getTotalSize().equals(fc2.getTotalSize()) ?
                        fc1.getName().compareTo(fc2.getName()) :
                        fc2.getTotalSize().compareTo(fc1.getTotalSize());
            }
        }).toList();
        return sortedFileCollections;
    }
}
