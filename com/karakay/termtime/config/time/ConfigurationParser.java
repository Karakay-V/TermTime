package com.karakay.termtime.config.time;

import com.karakay.termtime.tools.ConfigReader;

public class ConfigurationParser {
    private ConfigReader reader;

    public ConfigurationParser() {
        reader = new ConfigReader();
    }

    public TimeConfiguration getConfig() {
        return reader.getTimeConfig();
    }
}
