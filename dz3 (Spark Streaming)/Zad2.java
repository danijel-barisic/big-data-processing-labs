package rovp.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Zad2 {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("StateNameCount");

        //set the master if not already set through the command line
        try {
            conf.get("spark.master");
        } catch (NoSuchElementException ex) {
            conf.setMaster("local");
        }

        JavaSparkContext sc = new JavaSparkContext(conf);

        String filepath = "C:\\Users\\Mladen\\Desktop\\lab3rovp\\StateNames.csv";
        //crate an RDD from text file lines
        JavaRDD<String> lines = sc.textFile(filepath);

        //do the job
        JavaPairRDD<String, Long> result = lines.
                flatMap(line -> Arrays.asList(line.trim().split("\\s")).iterator()).
                map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim()).
                filter(word -> word.length() > 0).
                mapToPair(word -> new Tuple2<>(word, 1L)).
                reduceByKey(Long::sum);

        //write results to a file
        result.saveAsTextFile(args[1]);

        //Exception in thread "main" java.lang.NullPointerException: Cannot invoke "org.apache.spark.rpc.RpcEndpointRef.askSync(Object, scala.reflect.ClassTag)" because the return value of "org.apache.spark.storage.BlockManagerMaster.driverEndpoint()" is null
        //        at org.apache.spark.storage.BlockManagerMaster.registerBlockManager(BlockManagerMaster.scala:64)
        //        at org.apache.spark.storage.BlockManager.initialize(BlockManager.scala:248)
        //        at org.apache.spark.SparkContext.<init>(SparkContext.scala:510)
        //        at org.apache.spark.api.java.JavaSparkContext.<init>(JavaSparkContext.scala:58)
        //        at rovp.lab3.Zad2.main(Zad2.java:22)
        //        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        //        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:78)
        //        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        //        at java.base/java.lang.reflect.Method.invoke(Method.java:567)
        //        at org.apache.spark.deploy.JavaMainApplication.start(SparkApplication.scala:52)
        //        at org.apache.spark.deploy.SparkSubmit.org$apache$spark$deploy$SparkSubmit$$runMain(SparkSubmit.scala:849)
        //        at org.apache.spark.deploy.SparkSubmit.doRunMain$1(SparkSubmit.scala:167)
        //        at org.apache.spark.deploy.SparkSubmit.submit(SparkSubmit.scala:195)
        //        at org.apache.spark.deploy.SparkSubmit.doSubmit(SparkSubmit.scala:86)
        //        at org.apache.spark.deploy.SparkSubmit$$anon$2.doSubmit(SparkSubmit.scala:924)
        //        at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:933)
        //        at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)

        System.out.println("\n\n\nTEST: success yo!\n\n\n");
    }

}
