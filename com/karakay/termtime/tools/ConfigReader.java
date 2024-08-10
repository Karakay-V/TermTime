package com.karakay.termtime.tools;

import java.util.List;
import com.karakay.termtime.config.time.TimeConfiguration;
import com.karakay.termtime.config.time.TimeConfigurator;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import static com.karakay.termtime.tools.Print.*;

public class ConfigReader {
    private List<String> fileRows;
    public static final String confFileName = "term-time.conf";

    public ConfigReader(File file) {
        try {
            this.fileRows = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            printlnError(exception.getCause());
            printlnError(exception.getMessage());
        }
    }

    public ConfigReader(List<String> rows) {
        this.fileRows = rows;
    }

    public ConfigReader() {
        if (!isConfigFileExists()) {
            createDefaultConfig();
        }
        try {
            this.fileRows = Files.readAllLines(
                    new File(getConfigFileDir()
                            .concat(confFileName))
                            .toPath(),
                    StandardCharsets.UTF_8);
        } catch (IOException exception) {
            printlnError(exception.getCause());
            printlnError(exception.getMessage());
        }
    }

    private void createDefaultConfig() {
        new TimeConfigurator().configure();
    }

    public static boolean isConfigFileExists() {
        File configFile = new File(getConfigFileDir().concat(confFileName));
        if (configFile.exists() && !configFile.isDirectory()) {
            return true;
        }
        return false;
    }

    public static String getConfigFileDir() {
        String userHome = System.getProperty("user.home");
        String OSName = System.getProperty("os.name").toUpperCase();
        String confDir;
        if (OSName.contains("WINDOWS")) {
            confDir = userHome.concat(
                    File.separator
                            + "Documents"
                            + File.separator);
        } else {
            confDir = userHome.concat(
                    File.separator
                            + ".config"
                            + File.separator);
        }
        confDir = confDir.concat("term-time" + File.separator);
        return confDir;
    }

    public List<String> getAllRows() {
        return this.fileRows;
    }

    public List<Integer> findSimilarRows(String row) {
        List<Integer> similar = new ArrayList<Integer>();
        for (int i = 0; i < this.fileRows.size(); i++) {
            if (this.fileRows.get(i).contains(row.split("=")[0])) {
                similar.add(i + 1);
            }
        }
        return similar;
    }

    public int findSimilarRow(String row) {
        for (int i = 0; i < this.fileRows.size(); i++) {
            if (this.fileRows.get(i)
                    .contains(row.split("=")[0])) {
                return i + 1;
            }
        }
        return -1;
    }

    public TimeConfiguration getTimeConfig() {
        return new TimeConfiguration(
                fileRows.get(findSimilarRow("timeZoneRegion=") - 1).replaceAll("timeZoneRegion=", ""),
                fileRows.get(findSimilarRow("timeZoneHoursDifference=") - 1).replaceAll("timeZoneHoursDifference=",
                        ""));
    }
}
