package org.example;

import org.apache.hadoop.fs.FileSystem;

public class zad3 {
    public static void main(String[] args) {
        FileSystem.get(conf).delete(TEMP_RESULT, true);
    }
}
