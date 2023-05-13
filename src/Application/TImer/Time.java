package Application.TImer;

import Application.AppManager;

public class Time {

    final int millisecondHour = 3600000;
    final int millisecondMinute = 60000;
    final int millisecondSecond = 1000;
    long time;

    long timePause = 0;
    public Time() {
        setTimeNull();
    }
    public void setTimeNull() {
        time = System.currentTimeMillis();
    }

    public void setNull() {
        setTimeNull();
        timePause = 0;
    }
    public void setTime(long time) {
        this.time = time;
    }
    private long getTime() {
        return timePause + (System.currentTimeMillis() - time);
    }
    public String getTimeString() {
        String timeString = new String();
        Integer minutes = getMinutes();
        Integer seconds = getSeconds();
        Integer milliseconds = getMillisecond();
        timeString = intToString(minutes) + ":".toString() + intToString(seconds) + ".".toString() + intToString(milliseconds);
        return timeString;
    }

    public void setTimeS(String time) {
        timePause = 0;

    }

    private String intToString(Integer value) {
        String intString = new String();
        if (value < 1) {
            intString += "00";
        }
        else if (value < 10) {
            intString += "0";
            intString += value.toString();
        }
        else {
            intString += value.toString();
        }
        return intString;
    }
    public int getMinutes() {
        return (int)((getTime()%millisecondHour)/millisecondMinute);
    }

    public int getSeconds() {
        return (int)((getTime()%millisecondMinute)/millisecondSecond);
    }

    public int getMillisecond() {
        return (int)(getTime()%millisecondSecond)/10;
    }
    public int getTimeInt() {
        return (int)getTime();
    }
    public long getTimeLong() {
        return getTime();
    }
    private long getSystemTime() {
        return System.currentTimeMillis() - time;
    }
    public void setTimePause(long time) {
        this.timePause = time;
        setTimeNull();
    }

}
