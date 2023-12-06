package com.cs407.alarm_clock;
import java.util.ArrayList;
import java.util.List;

public class Alarm {
    private int hour;
    private int minute;
    private boolean isAM;
    private String note;
    private boolean isRepeatable;
    private List<Integer> repeatDays;

    public Alarm(int hour, int minute, boolean isAM, String note, boolean isRepeatable, List<Integer> repeatDays) {
        this.hour = hour;
        this.minute = minute;
        this.isAM = isAM;
        this.note = note;
        this.isRepeatable = isRepeatable;
        this.repeatDays = repeatDays;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getNote() {
        return note;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public List<Integer> getRepeatDays() {
        return repeatDays;
    }

    public boolean isAM() {
        return isAM;
    }
}
