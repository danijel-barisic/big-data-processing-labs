package rovp.lab3;

import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Zad3 {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("SensorReading");
        try {
            conf.get("spark.master");
        } catch (NoSuchElementException ex) {
            conf.setMaster("local");
        }

        String filename="C:\\Users\\Mladen\\Desktop\\lab3rovp\\sensorscope-monitor-all.csv";

        // No apache?
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(10));
        JavaDStream<String> lines = jssc.socketTextStream("localhost", 9870);
        JavaPairDStream<String, Long> result = lines.
                flatMap(line -> Arrays.asList(line.trim().split("\\s"))).
                filter(word -> word.length() > 0).
                mapToPair(word -> new Tuple2<>(word, 1L)).
                reduceByKey((x, y) -> x + y);
        result.dstream().saveAsTextFiles(args[0], "txt");

        jssc.start();
        jssc.awaitTermination();
    }
}
