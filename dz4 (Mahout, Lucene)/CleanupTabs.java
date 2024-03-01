package hr.fer.rovp.lab4.zad2;

import java.io.*;

public class CleanupTabs {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Mladen\\Downloads\\jester-dataset\\jester_ratings.dat"));
             PrintWriter writer = new PrintWriter("jester_ratings.dat")) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("\t\t", ",");
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
