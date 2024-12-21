package atlassian2024.fileprocessing.algo;

import atlassian2024.fileprocessing.dto.FileCollection;

import java.util.List;
import java.util.Map;

public interface ITopKStrategy {
    List<FileCollection> getTopKCollections(int k, Map<String, FileCollection> nameToFileCollectionMap);
}
