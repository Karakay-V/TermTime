package com.karakay.termtime.config.time;

import java.io.Serializable;

public class TimeConfiguration implements Serializable {
    public String timeZoneRegion;
    public String timeZoneHoursDifference;

    public String toString() {
        return "timeZoneRegion: " + this.timeZoneRegion +
                "\ntimeZoneHoursDifference: " + this.timeZoneHoursDifference;
    }

    public TimeConfiguration() {
        this.timeZoneRegion = "Europe/Kyiv";
        this.timeZoneHoursDifference = "+03:00";
    }

    public TimeConfiguration(String region, String timeDifference) {
        this.timeZoneRegion = region;
        this.timeZoneHoursDifference = timeDifference;
    }
}