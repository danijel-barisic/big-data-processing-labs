package rovp.lab3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class Zad1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\Mladen\\Desktop\\lab3rovp\\sensorscope-monitor");
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Mladen\\Desktop\\" +
                "lab3rovp\\sensorscope-monitor-all.csv"));
        try {
            Stream<Path> pathStream = Files.list(path);
            pathStream
                    .filter(Zad1::filenameFilterCondition)
                    .map(Zad1::fileLinesMapper)
                    .reduce(Stream::concat)
                    .orElse(Stream.empty())
                    .filter(Zad1::isParsable)
                    .map(Zad1::lineSensorscopeReadingMapper)
                    .sorted(Comparator
                            .comparing(SensorscopeReading::getYear)
                            .thenComparing(SensorscopeReading::getMonth)
                            .thenComparing(SensorscopeReading::getDay)
                            .thenComparing(SensorscopeReading::getHour)
                            .thenComparing(SensorscopeReading::getMinute)
                            .thenComparing(SensorscopeReading::getSecond))
                    .forEach(sr -> {
                        try {
                            bw.write(sr.toString()+"\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean filenameFilterCondition(Path x) {
        return x.getFileName().toString().startsWith("sensorscope-monitor") &&
                !(x.getFileName().toString().endsWith("def.txt"));
    }

    private static Stream<String> fileLinesMapper(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    private static boolean isParsable(String line) {
        String[] parameters = line.split("\\s+");
        if (parameters.length != 19) return false;
        for (int i = 0; i < 9; i++) {
            if (!isInteger(parameters[i])) return false;
        }
        for (int i = 9; i < 19; i++) {
            if (!isDouble(parameters[i])) return false;
        }
        return true;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static SensorscopeReading lineSensorscopeReadingMapper(String line) {
        String[] parameters = line.split("\\s+");
        SensorscopeReading res = new SensorscopeReading();
        res.setStationID(Integer.parseInt(parameters[0]));
        res.setYear(Integer.parseInt(parameters[1]));
        res.setMonth(Integer.parseInt(parameters[2]));
        res.setDay(Integer.parseInt(parameters[3]));
        res.setHour(Integer.parseInt(parameters[4]));
        res.setMinute(Integer.parseInt(parameters[5]));
        res.setSecond(Integer.parseInt(parameters[6]));
        res.setTimeSinceEpoch(Integer.parseInt(parameters[7]));
        res.setSeqNum(Integer.parseInt(parameters[8]));
        res.setConfigSamplingTime(Double.parseDouble(parameters[9]));
        res.setDataSamplingTime(Double.parseDouble(parameters[10]));
        res.setRadioDutyCycle(Double.parseDouble(parameters[11]));
        res.setRadioTransPower(Double.parseDouble(parameters[12]));
        res.setRadioTransFreq(Double.parseDouble(parameters[13]));
        res.setPrimaryBufferVolt(Double.parseDouble(parameters[14]));
        res.setSecondaryBufferVolt(Double.parseDouble(parameters[15]));
        res.setSolarPanelCurrent(Double.parseDouble(parameters[16]));
        res.setGlobalCurrent(Double.parseDouble(parameters[17]));
        res.setEnergySource(Double.parseDouble(parameters[18]));

        return res;
    }

}
