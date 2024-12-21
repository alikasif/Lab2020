package fileprocessing;

import atlassian2024.fileprocessing.algo.TopKByFileCount;
import atlassian2024.fileprocessing.algo.TopKBySizeUsingPQ;
import atlassian2024.fileprocessing.dto.File;
import atlassian2024.fileprocessing.dto.FileCollection;
import atlassian2024.fileprocessing.processor.FileProcessor;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class FileProcessorTest {

    @Test
    public void testFileProcessor() {

        FileProcessor fp = new FileProcessor(new TopKByFileCount());

        fp.addFile(new File("file1", 100L, null));

        fp.addFile(new File("file2", 200L, "collection1"));
        fp.addFile(new File("file3", 200L, "collection1"));
        fp.addFile(new File("file4", 300L, "collection2"));
        fp.addFile(new File("file5", 100L, null));
        fp.addFile(new File("file6", 100L, null));

        List<FileCollection> topKCollections = fp.getTopKCollections(6);

        Assert.assertEquals("DEFAULT", topKCollections.get(0).getName());
        Assert.assertEquals("collection1", topKCollections.get(1).getName());
        Assert.assertEquals("collection2", topKCollections.get(2).getName());
    }

    @Test
    public void getTopOnEmptyCollection() {
        FileProcessor fp = new FileProcessor(new TopKByFileCount());
        List<FileCollection> topKCollections = fp.getTopKCollections(3);
        Assert.assertTrue(topKCollections.isEmpty());
    }

    @Test
    public void sameFileInAllCollection() {
        FileProcessor fp = new FileProcessor(new TopKBySizeUsingPQ());
        fp.addFile(new File("file1", 200L, "collection1"));
        fp.addFile(new File("file1", 200L, "collection2"));
        fp.addFile(new File("file1", 200L, "collection3"));
        List<FileCollection> topKCollections = fp.getTopKCollections(6);

        System.out.println(topKCollections);

        Assert.assertEquals("collection1", topKCollections.get(0).getName());
        Assert.assertEquals("collection2", topKCollections.get(1).getName());
        Assert.assertEquals("collection3", topKCollections.get(2).getName());
    }

    @Test
    public void filesWithoutCollection() {
        FileProcessor fp = new FileProcessor(new TopKBySizeUsingPQ());
        fp.addFile(new File("file1", 100L, null));
        fp.addFile(new File("file2", 200L, null));
        fp.addFile(new File("file3", 300L, null));
        List<FileCollection> topKCollections = fp.getTopKCollections(3);

        System.out.println(topKCollections);
        Assert.assertEquals(1, topKCollections.size());
        Assert.assertEquals("DEFAULT", topKCollections.get(0).getName());
    }

    @Test
    public void smallAndLargeFiles() {
        FileProcessor fp = new FileProcessor(new TopKBySizeUsingPQ());
        fp.addFile(new File("file1", 100L, "collection1"));
        fp.addFile(new File("file2", 100L, "collection1"));
        fp.addFile(new File("file3", 100L, "collection1"));
        fp.addFile(new File("file4", 600L, "collection2"));

        List<FileCollection> topKCollections = fp.getTopKCollections(3);

        System.out.println(topKCollections);

        Assert.assertEquals(2, topKCollections.size());
        Assert.assertEquals("collection2", topKCollections.get(0).getName());
        Assert.assertEquals("collection1", topKCollections.get(1).getName());
    }

    @Test
    public void negativeK() {
        FileProcessor fp = new FileProcessor(new TopKBySizeUsingPQ());
        fp.addFile(new File("file1", 100L, "collection1"));
        fp.addFile(new File("file2", 100L, "collection1"));
        fp.addFile(new File("file3", 100L, "collection1"));
        fp.addFile(new File("file4", 600L, "collection2"));

        List<FileCollection> topKCollections = fp.getTopKCollections(-3);

        System.out.println(topKCollections);

        Assert.assertEquals(2, topKCollections.size());
        Assert.assertEquals("collection2", topKCollections.get(0).getName());
        Assert.assertEquals("collection1", topKCollections.get(1).getName());
    }




}
