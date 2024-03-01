package rovp.lab3;


/*
1. Station ID
2. Year
3. Month
4. Day
5. Hour
6. Minute
7. Second
8. Time since the epoch [s]
9. Sequence Number
10. Config Sampling Time [s]
11. Data Sampling Time [s]
12. Radio Duty Cycle [%]
13. Radio Transmission Power [dBm]
14. Radio Transmission Frequency [MHz]
15. Primary Buffer Voltage [V]
16. Secondary Buffer Voltage [V]
17. Solar Panel Current [mA]
18. Global Current [mA]
19. Energy Source
 */
public class SensorscopeReading {

    private int stationID;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int timeSinceEpoch;
    private int seqNum;
    private double configSamplingTime;
    private double dataSamplingTime;
    private double radioDutyCycle;
    private double radioTransPower;
    private double radioTransFreq;
    private double primaryBufferVolt;
    private double secondaryBufferVolt;
    private double solarPanelCurrent;
    private double globalCurrent;
    private double energySource;

    public int getStationID() {
        return stationID;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getTimeSinceEpoch() {
        return timeSinceEpoch;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public double getConfigSamplingTime() {
        return configSamplingTime;
    }

    public double getDataSamplingTime() {
        return dataSamplingTime;
    }

    public double getRadioDutyCycle() {
        return radioDutyCycle;
    }

    public double getRadioTransPower() {
        return radioTransPower;
    }

    public double getRadioTransFreq() {
        return radioTransFreq;
    }

    public double getPrimaryBufferVolt() {
        return primaryBufferVolt;
    }

    public double getSecondaryBufferVolt() {
        return secondaryBufferVolt;
    }

    public double getSolarPanelCurrent() {
        return solarPanelCurrent;
    }

    public double getGlobalCurrent() {
        return globalCurrent;
    }

    public double getEnergySource() {
        return energySource;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setTimeSinceEpoch(int timeSinceEpoch) {
        this.timeSinceEpoch = timeSinceEpoch;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public void setConfigSamplingTime(double configSamplingTime) {
        this.configSamplingTime = configSamplingTime;
    }

    public void setDataSamplingTime(double dataSamplingTime) {
        this.dataSamplingTime = dataSamplingTime;
    }

    public void setRadioDutyCycle(double radioDutyCycle) {
        this.radioDutyCycle = radioDutyCycle;
    }

    public void setRadioTransPower(double radioTransPower) {
        this.radioTransPower = radioTransPower;
    }

    public void setRadioTransFreq(double radioTransFreq) {
        this.radioTransFreq = radioTransFreq;
    }

    public void setPrimaryBufferVolt(double primaryBufferVolt) {
        this.primaryBufferVolt = primaryBufferVolt;
    }

    public void setSecondaryBufferVolt(double secondaryBufferVolt) {
        this.secondaryBufferVolt = secondaryBufferVolt;
    }

    public void setSolarPanelCurrent(double solarPanelCurrent) {
        this.solarPanelCurrent = solarPanelCurrent;
    }

    public void setGlobalCurrent(double globalCurrent) {
        this.globalCurrent = globalCurrent;
    }

    public void setEnergySource(double energySource) {
        this.energySource = energySource;
    }

    @Override
    public String toString() {
        return stationID +
                "," + year +
                "," + month +
                "," + day +
                "," + hour +
                "," + minute +
                "," + second +
                "," + timeSinceEpoch +
                "," + seqNum +
                "," + configSamplingTime +
                "," + dataSamplingTime +
                "," + radioDutyCycle +
                "," + radioTransPower +
                "," + radioTransFreq +
                "," + primaryBufferVolt +
                "," + secondaryBufferVolt +
                "," + solarPanelCurrent +
                "," + globalCurrent +
                "," + energySource;
    }

    //
//    @Override
//    public int compareTo(SensorscopeReading o) {
//        if (this.year != o.year) {
//            return this.year - o.year;
//        }
//        if (this.month != o.month) {
//            return this.month - o.month;
//        }
//        if (this.day != o.day) {
//            return this.day - o.day;
//        }
//        if (this.hour != o.hour) {
//            return this.hour - o.hour;
//        }
//        if (this.minute != o.minute) {
//            return this.minute - o.minute;
//        }
//        if (this.second != o.second) {
//            return this.second - o.second;
//        }
//
//        return 0;
//    }
}
