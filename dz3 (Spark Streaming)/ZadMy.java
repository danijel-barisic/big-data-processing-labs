package rovp.lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ZadMy {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Mladen\\Desktop\\lab3rovp\\StateNames.csv");
        Scanner sc = new Scanner(file);
        long total=0;

        Map<String, Integer> map = new HashMap<>();
        boolean firstLine = true;
        while (sc.hasNext()) {
            if (firstLine) {
                sc.nextLine();
                firstLine = false;
                continue;
            }

            String line = sc.nextLine();

            String[] split = line.split(",");
            String name = split[1];
            int year = Integer.parseInt(split[2]);
            String gender = split[3];
            String state = split[4];
            int count = Integer.parseInt(split[5]);

//            if (map.containsKey(state)) {
//                count += map.get(state);
//            }
//            if (year == 1948) {
//                map.put(state, count);
//            }
            if (map.containsKey(name)) {
                count += map.get(name);
            }
//
//            if(gender.equals("F"))
                map.put(name, count);
            //total+=count;
        }
        System.out.println(map.keySet().size());

//        // find minimum sum
//        int minFreq = Integer.MAX_VALUE;
//        String minName = null;
//        for (String name : map.keySet()) {
//            if (map.get(name) < minFreq) {
//                minFreq = map.get(name);
//                minName = name;
//            }
//        }
//
//        System.out.println("Least popular name count: " + minFreq);
//        System.out.println("Least popular name : " + minName);

        // find minimum sum
//        int maxFreq = Integer.MIN_VALUE;
//        String maxState = null;
//        for (String state : map.keySet()) {
//            if (map.get(state) > maxFreq) {
//                maxFreq = map.get(state);
//                maxState = state;
//            }
//        }
//
//        System.out.println("Freq: " + maxFreq);
//        System.out.println("State: " + maxState);

//        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(10, (a, b) -> b.getValue() - a.getValue());
//        // add all entries to the max heap
//        maxHeap.addAll(map.entrySet());
//        // extract 10 entries from the max heap
//        for (int i = 0; i < 10; i++) {
//            Map.Entry<String, Integer> entry = maxHeap.poll();
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
    }
}
