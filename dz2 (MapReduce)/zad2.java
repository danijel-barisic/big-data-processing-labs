package org.example;

import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class zad2 {
    public static void main(String[] args) throws IOException {
        Job job = Job.getInstance();
        job.setJarByClass(zad1.class);
        job.setJobName("Zad2");
    }
}
