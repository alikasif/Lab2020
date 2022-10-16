package spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;

public class WordCount {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("testapp").setMaster("local");
        SparkContext sparkContext = new SparkContext(conf);
        RDD<String> stringRDD = sparkContext.textFile("sample.txt", 3);
        System.out.println(stringRDD.count());

    }
}
