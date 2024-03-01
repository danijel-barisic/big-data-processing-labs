package org.example;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class CountMinMaxTuple implements WritableComparable<CountMinMaxTuple> {

    private final LongWritable tripTime;
    private final LongWritable min;
    private final LongWritable max;

    public CountMinMaxTuple(LongWritable tripTime, LongWritable min, LongWritable max) {
        this.tripTime = tripTime;
        this.min = min;
        this.max = max;
    }

    @Override
    public int compareTo(CountMinMaxTuple o) {
        int cmp1 = tripTime.compareTo(o.tripTime);
        if (cmp1 != 0) {
            return cmp1;
        }

        int cmp2 = min.compareTo(o.min);
        if (cmp2 != 0) {
            return cmp2;
        }

        return max.compareTo(o.max);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        tripTime.write(dataOutput);
        min.write(dataOutput);
        max.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        tripTime.readFields(dataInput);
        min.readFields(dataInput);
        max.readFields(dataInput);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountMinMaxTuple that = (CountMinMaxTuple) o;
        return Objects.equals(tripTime, that.tripTime) && Objects.equals(min, that.min) && Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripTime, min, max);
    }

    public LongWritable getTripTime() {
        return tripTime;
    }

    public LongWritable getMin() {
        return min;
    }

    public LongWritable getMax() {
        return max;
    }
}
