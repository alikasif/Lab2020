package atlassian2024.fileprocessing.dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionMapper {

    private Map<String, FileCollection> nameToCollectionMap;

    public CollectionMapper() {
        nameToCollectionMap = new ConcurrentHashMap<>();
    }

    public void addFileToCollection(File file) {

        nameToCollectionMap.putIfAbsent(file.collectionName, new FileCollection(file.collectionName));

        nameToCollectionMap.get(file.collectionName).addFile(file);

    }

    public Map<String, FileCollection> getNameToCollectionMap() {
        return nameToCollectionMap;
    }
}
