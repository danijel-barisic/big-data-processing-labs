package org.example;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TotalTripTimeMapper extends Mapper<LongWritable, Text, Text, CountMinMaxTuple> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, CountMinMaxTuple>.Context context)
            throws IOException, InterruptedException {
        if (key.get() > 0) {
            String row = value.toString();
            String[] split = row.split(",");

            context.write(new Text(split[0]), new CountMinMaxTuple(new LongWritable(Long.parseLong(split[8])),
                    new LongWritable(Long.parseLong(split[8])), new LongWritable(Long.parseLong(split[8]))));
        }
    }
}
