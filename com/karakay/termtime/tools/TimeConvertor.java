package com.karakay.termtime.tools;

import static com.karakay.termtime.tools.Print.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.List;

public class TimeConvertor {
    public static long toEpochTime(String str) {
        // Must be look: HH:MM:SS
        byte[] bytesArrTime = toBytesArrTime(str);
        return toEpochSeconds(bytesArrTime[0], bytesArrTime[1], bytesArrTime[2]);
    }

    public static long toEpochTime(byte hoursArg, byte minutesArg, byte secondsArg) {
        return toEpochSeconds(hoursArg, minutesArg, secondsArg);
    }

    public static String toStringTime(long epochTime, String timeZone) {
        return toDateString(epochTime, timeZone);
    }

    public static String toShortStringTime(long epochTime, String timeZone) {
        return toDateString(epochTime, timeZone).substring(11, 19);
    }

    public static List<String> toBytesFromArgs(List<String> timeArgs) {
        char[] charArr = new char[] { 'h', 'm', 's' };
        byte[] byteArr = new byte[3];
        for (byte i = 0; i < timeArgs.size(); i++) {
            String tmpArg = timeArgs.get(i);
            for (byte j = 0; j < charArr.length; j++) {
                String tmpStr = ((Character) charArr[j]).toString();
                if (tmpArg.contains(tmpStr)) {
                    try {
                        byteArr[j] = Byte.valueOf(tmpArg.substring(0, tmpArg.indexOf(tmpStr)));
                    } catch (IndexOutOfBoundsException e) {
                        printlnError(e.getCause());
                    }
                    break;
                }
            }
        }
        List<String> result = new ArrayList<String>();
        for (byte k = 0; k < byteArr.length; k++) {
            result.add(((Byte) byteArr[k]).toString());
        }
        return result;
        // NOTE: must return a list with bytes like [HH, MM, SS]
    }

    private static String toDateString(long epochTime, String timeZoneId) {
        Date date = new Date(epochTime * 1000L);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timeZoneId)));
        return format.format(date);
    }

    private static long toEpochSeconds(byte hoursArg, byte minutesArg, byte secondsArg) {
        return (((hoursArg * 60) * 60) + (minutesArg * 60) + secondsArg);
    }

    private static byte[] toBytesArrTime(String str) {
        String[] splStr = str.trim().split(":");
        byte hoursArg = Byte.parseByte(String.valueOf(new char[] { splStr[0].charAt(0), splStr[0].charAt(1) }));
        byte minutesArg = Byte.parseByte(String.valueOf(new char[] { splStr[1].charAt(0), splStr[1].charAt(1) }));
        byte secondsArg = Byte.parseByte(String.valueOf(new char[] { splStr[2].charAt(0), splStr[2].charAt(1) }));
        return new byte[] { hoursArg, minutesArg, secondsArg };
    }
}
