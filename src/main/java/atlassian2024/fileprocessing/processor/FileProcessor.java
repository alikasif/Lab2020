package atlassian2024.fileprocessing.processor;

import atlassian2024.fileprocessing.algo.ITopKStrategy;
import atlassian2024.fileprocessing.dto.File;
import atlassian2024.fileprocessing.dto.FileCollection;
import atlassian2024.fileprocessing.dto.CollectionMapper;

import java.util.List;

public class FileProcessor {

    CollectionMapper fileToCollectionMapper;
    ITopKStrategy strategy;
    public FileProcessor(ITopKStrategy strategy){
        this.strategy = strategy;
        fileToCollectionMapper = new CollectionMapper();
    }

    public void addFile(File file){
        fileToCollectionMapper.addFileToCollection(file);
    }

    public List<FileCollection> getTopKCollections(int k) {
        return strategy.getTopKCollections(k, fileToCollectionMapper.getNameToCollectionMap());
    }
}
