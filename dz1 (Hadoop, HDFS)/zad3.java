import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class zad3 {

    public static void main(String[] args) throws URISyntaxException {

        long fileCounter = 0;
        long lineCounter = 0;
        Configuration configuration = new Configuration();
        try {
            FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000"), configuration);
            LocalFileSystem lfs = LocalFileSystem.getLocal(configuration);
            Path locPath = Paths.get("C:/Users/Osobno/Desktop/ROVKP_DZ1/gutenberg/");
            Path hdfsPath = Paths.get("hdfs://localhost:9000");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        try {
            for (FileStatus fileStatus : localFileSystem.listStatus(source)) {
                if (fileStatus.isFile()) {
                    fileCounter++;
                }
                lineCounter+= Files.lines(fileStatus).count()
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
