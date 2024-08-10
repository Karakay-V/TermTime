package com.karakay.termtime.tools;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ConfigWriter {
    private List<String> fileRows;
    private ConfigReader reader;

    public ConfigWriter(File file) throws IOException {
        this.fileRows = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        this.reader = new ConfigReader(file);
    }

    public ConfigWriter(List<String> rows) {
        this.fileRows = rows;
        this.reader = new ConfigReader(rows);
    }

    public ConfigWriter() throws IOException {
        File file = createConfigFile();
        this.fileRows = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        this.reader = new ConfigReader(file);
    }

    private File createConfigFile() throws IOException {
        new File(ConfigReader.getConfigFileDir()).mkdirs();
        File configFile = new File(ConfigReader
                .getConfigFileDir()
                .concat(ConfigReader.confFileName));
        configFile.createNewFile();
        return configFile;
    }

    public void addSection(String section, List<String> dataRows) {
        int exsSection = this.reader.findSimilarRow(section);
        if (exsSection != -1) {
            this.addRowsToSection(exsSection, dataRows);
        } else {
            this.fileRows.add(this.fileRows.size(), section);
            if (this.fileRows.get(this.fileRows.size() - 1).equals(section)) {
                exsSection = this.fileRows.size() - 1;
            }
            this.addRowsToSection(exsSection, dataRows);
        }
    }

    private void addRowsToSection(int exsSection, List<String> dataRows) {
        dataRows.forEach((newRow) -> {
            int oldRowNumb = this.reader.findSimilarRow(newRow);
            if (oldRowNumb != -1) {
                this.fileRows.remove(oldRowNumb - 1);
                this.fileRows.add(oldRowNumb - 1, newRow);
            } else {
                this.fileRows.add(exsSection + 1, newRow);
            }
        });
    }

    public void writeFile() throws IOException {
        FileWriter writer = new FileWriter(
                ConfigReader.getConfigFileDir()
                        .concat(ConfigReader.confFileName));
        this.fileRows.forEach((row) -> {
            try {
                writer.write(row + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
}
