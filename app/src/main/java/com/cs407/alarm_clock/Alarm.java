package com.cs407.alarm_clock;

import java.util.List;

public class Alarm {
    private long id;
    private int hour;
    private int minute;
    private String note;
    private boolean isRepeatable;
    private List<Integer> repeatDays;
    private boolean isAM;

    public Alarm(long id, int hour, int minute, String note, boolean isRepeatable, List<Integer> repeatDays, boolean isAM) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.note = note;
        this.isRepeatable = isRepeatable;
        this.repeatDays = repeatDays;
        this.isAM = isAM;
    }

    public long getId() {
        return id;
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

    public String getTimeAsString() { return hour + ":" +
            (String.valueOf(minute).length() == 1 ? "0" + minute : minute) +
            " " + (isAM ? "AM" : "PM"); }
}
