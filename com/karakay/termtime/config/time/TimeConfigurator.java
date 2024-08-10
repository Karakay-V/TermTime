package com.karakay.termtime.config.time;

import com.karakay.termtime.config.IConfigurator;
import com.karakay.termtime.tools.ConfigWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class TimeConfigurator implements IConfigurator {
    private static final Map<String, String> ALL_TIME_ZONES = new HashMap<String, String>();
    private TimeConfiguration timeConfiguration;

    public TimeConfigurator() {
        this.timeConfiguration = new TimeConfiguration();
    }

    public static Map<String, String> getAllTimeZones() {
        if (ALL_TIME_ZONES.isEmpty()) {
            setTimeZonesMap(ALL_TIME_ZONES);
        }
        return ALL_TIME_ZONES;
    }

    private static void setTimeZonesMap(Map<String, String> timeZonesMap) {
        List<String> zoneIds = new ArrayList<String>(ZoneId.getAvailableZoneIds());
        Collections.sort(zoneIds);
        Collections.reverse(zoneIds);
        zoneIds.forEach((id) -> timeZonesMap.put(id, Instant.now().atZone(ZoneId.of(id))
                .getOffset()
                .toString()
                .replace("Z", "+00:00")));
    }

    public void setTimeZone(String strTimeZone) {
        Map<String, String> allTimeZones = getAllTimeZones();
        if (allTimeZones.containsKey(strTimeZone)) {
            this.timeConfiguration.timeZoneRegion = strTimeZone;
            this.timeConfiguration.timeZoneHoursDifference = allTimeZones.get(strTimeZone);
        } else if (allTimeZones.containsValue(strTimeZone)) {
            // List<String> Regions
            allTimeZones.forEach((k, v) -> {
                this.timeConfiguration.timeZoneRegion = k;
            });
            this.timeConfiguration.timeZoneHoursDifference = strTimeZone;
        }
    }

    public TimeConfiguration getConfig() {
        return this.timeConfiguration;
    }

    public void configure() {
        try {
            ConfigWriter writer = new ConfigWriter();
            List<String> sectionData = new ArrayList<String>();
            sectionData.add("# $ time-term config getTimeZones");
            sectionData.add("# All compatible regions you can see after run");
            sectionData.add("timeZoneHoursDifference=" + this.timeConfiguration.timeZoneHoursDifference);
            sectionData.add("timeZoneRegion=" + this.timeConfiguration.timeZoneRegion);
            writer.addSection("[General]", sectionData);
            writer.writeFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
