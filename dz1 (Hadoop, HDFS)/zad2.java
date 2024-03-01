import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class zad2 {

    public static void main(String[] args) {
        Path targetDir = Paths.get("C:/Users/Osobno/Desktop/ROVKP_DZ1/gutenberg/");

        final AtomicLong fileCounter = new AtomicLong();
        final AtomicLong lineCounter = new AtomicLong();
        try (Stream<Path> stream = Files.walk(Paths.get(targetDir.toUri()))) {
            stream.forEach(path -> {
                try {
                    fileCounter.getAndIncrement();
                    lineCounter.addAndGet(Files.lines(path).count());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Number of files: " + fileCounter + ", number of lines: " + lineCounter);
    }

}
