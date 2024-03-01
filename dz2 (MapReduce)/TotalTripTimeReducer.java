package org.example;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TotalTripTimeReducer extends Reducer<Text, CountMinMaxTuple, Text, CountMinMaxTuple> {
    @Override
    protected void reduce(Text key, Iterable<CountMinMaxTuple> values, Reducer<Text, CountMinMaxTuple, Text, CountMinMaxTuple>.Context context) throws IOException, InterruptedException {
        long totalTripTime = 0L;
        long minTripTime = Long.MAX_VALUE;
        long maxTripTime = 0L;

        for (CountMinMaxTuple t : values) {
            totalTripTime += t.getTripTime().get();
            minTripTime = Math.min(minTripTime, t.getMin().get());
            maxTripTime = Math.max(maxTripTime, t.getMax().get());
        }

        context.write(key, new CountMinMaxTuple(new LongWritable(totalTripTime), new LongWritable(minTripTime),
                new LongWritable(maxTripTime)));
    }

}
