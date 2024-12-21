package atlassian2024.fileprocessing.dto;

public class File {

    String name;
    Long size;
    String collectionName;

    static String defaultCollectionName = "DEFAULT";

    public File(String name, Long size, String collectionName) {
        this.name = name;
        this.size = size;
        if (collectionName == null)
            collectionName = defaultCollectionName;

        this.collectionName = collectionName;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                '}';
    }
}
