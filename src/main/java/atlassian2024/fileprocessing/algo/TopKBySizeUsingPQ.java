package atlassian2024.fileprocessing.algo;

import atlassian2024.fileprocessing.dto.FileCollection;

import java.util.*;

public class TopKBySizeUsingPQ implements ITopKStrategy {

    PriorityQueue<FileCollection> pq;

    public TopKBySizeUsingPQ(){
    }

    private void initPQ(int k) {
        pq = new PriorityQueue<FileCollection>(k, new Comparator<FileCollection>() {
            @Override
            public int compare(FileCollection fc1, FileCollection fc2) {
                return fc1.getTotalSize().equals(fc2.getTotalSize()) ?
                        fc1.getName().compareTo(fc2.getName()) :
                        fc2.getTotalSize().compareTo(fc1.getTotalSize());
            }
        });
    }

    @Override
    public List<FileCollection> getTopKCollections(int k, Map<String, FileCollection> nameToFileCollectionMap) {

        if (k<=0)
            throw new IllegalArgumentException("negative K is not supported");

        initPQ(k);
        pq.addAll(nameToFileCollectionMap.values());

        List<FileCollection> lst = new ArrayList<>();
        while (!pq.isEmpty()) {
            lst.add(pq.remove());
        }

        return lst;
    }
}
