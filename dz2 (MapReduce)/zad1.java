package org.example;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import org.apache.hadoop.fs.Path;

public class zad1 {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = Job.getInstance();
        job.setJarByClass(zad1.class);
        job.setJobName("Zad1");
        JobConf jc = new JobConf(job.getConfiguration());

        FileInputFormat.addInputPath(jc, new Path("/rovkp/trip_data_small.csv"));
        FileOutputFormat.getOutputPath(jc);

        job.setMapperClass(TotalTripTimeMapper.class);
        job.setCombinerClass(TotalTripTimeReducer.class);
        job.setReducerClass(TotalTripTimeReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(CountMinMaxTuple.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
