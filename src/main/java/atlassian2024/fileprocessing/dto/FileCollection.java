package atlassian2024.fileprocessing.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FileCollection {

    @Getter
    String name;
    List<File> files;

    @Getter
    Long totalSize;

    public FileCollection(String name) {
        this.name = name;
        files = new ArrayList<>();
        totalSize=0L;
    }

    public void addFile(File file) {
        files.add(file);
        totalSize+= file.size;
    }

    public Integer getFileCount(){
        return files.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileCollection that = (FileCollection) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "FileCollection{" +
                "name='" + name + '\'' +
                ", files=" + files +
                ", totalSize=" + totalSize +
                '}';
    }
}
