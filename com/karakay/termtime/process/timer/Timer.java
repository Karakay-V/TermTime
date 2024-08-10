package com.karakay.termtime.process.timer;

import java.time.Instant;
import java.lang.Comparable;
import static com.karakay.termtime.tools.Print.*;
import static com.karakay.termtime.tools.TimeConvertor.*;
import com.karakay.termtime.config.time.ConfigurationParser;
import com.karakay.termtime.tools.Notificator;
import com.karakay.termtime.tools.TimeConvertor;

public class Timer implements Comparable<Timer>, Runnable {
    private byte endHours;
    private byte endMinutes;
    private byte endSeconds;
    private long startTime;
    private long progressTime;
    private long endTime;
    private ConfigurationParser configParser;
    protected Notificator notificator;
    private final Thread currThread = Thread.currentThread();

    public void run() {
        do {
            try {
                this.currThread.sleep(1000L);
                this.progressTime += 1L;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (this.progressTime < this.endTime);

        this.notificator.sendNotification("Timer has been expired!", "The timer created in "
                + TimeConvertor.toShortStringTime(this.startTime, this.configParser.getConfig().timeZoneHoursDifference)
                + " has been expired.");
    }

    public void update(Timer anotherTimerThread) {
        if (!this.equals(anotherTimerThread)) {
            this.startTime = anotherTimerThread.startTime;
            this.endTime = anotherTimerThread.endTime;
        }
    }

    public void setNotificator(Notificator notificatorArg) {
        this.notificator = notificatorArg;
    }

    public void show() {
        println("Running this from " + Thread.currentThread().getName() + " thread.");
        println("hours: " + this.endHours + " | minutes: " + this.endMinutes + " | seconds: " + this.endSeconds);
        println("current time: " + this.startTime);
        println("scheduled time: " + this.endTime);
    }

    public Timer(String strTime, Notificator notificatorArg) {
        setTimeCredentials(toEpochTime(strTime));
        setNotificator(notificatorArg);
        configParser = new ConfigurationParser();
    }

    public Timer(byte hoursArg, byte minutesArg, byte secondsArg, Notificator notificatorArg) {
        setTimeCredentials(Instant.now().getEpochSecond() + toEpochTime(hoursArg, minutesArg, secondsArg));
        setNotificator(notificatorArg);
        configParser = new ConfigurationParser();
    }

    public Timer(long epochTimeArg, Notificator notificatorArg) {
        setTimeCredentials(epochTimeArg);
        setNotificator(notificatorArg);
        configParser = new ConfigurationParser();
    }

    private void setTimeCredentials(byte hoursArg, byte minutesArg, byte secondsArg) {
        if (((hoursArg >= 0 && hoursArg < 24)
                && (minutesArg >= 0 && minutesArg < 60)
                && (secondsArg >= 0 && secondsArg < 60))) {
            this.endHours = hoursArg;
            this.endMinutes = minutesArg;
            this.endSeconds = secondsArg;
            this.startTime = Instant.now().getEpochSecond();
            this.progressTime = startTime;
            this.endTime = startTime + (((hoursArg * 60) * 60) + (minutesArg * 60) + secondsArg);
        } else {
            printlnError("Invalid value arguments for Timer constructor.");
        }
    }

    private void setTimeCredentials(long epochTimeArg) {
        Instant currTimeInstant = Instant.now();
        if (currTimeInstant.getEpochSecond() < epochTimeArg) {
            this.startTime = currTimeInstant.getEpochSecond();
            this.progressTime = startTime;
            this.endTime = epochTimeArg;
        } else {
            printlnError("Invalid value arguments for Timer constructor.");
        }
    }

    public boolean equals(Timer anotherTimer) {
        return this.startTime == anotherTimer.startTime &&
                this.endTime == anotherTimer.endTime;
    }

    public int compareTo(Timer anotherTimer) {
        if (this.startTime == anotherTimer.startTime &&
                this.endTime == anotherTimer.endTime) {
            return 0;
        } else if (this.startTime > anotherTimer.startTime) {
            return 1;
        } else {
            return -1;
        }
    }
}
