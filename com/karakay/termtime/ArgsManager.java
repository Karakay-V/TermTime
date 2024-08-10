package com.karakay.termtime;

import com.karakay.termtime.config.time.ConfigurationParser;
import com.karakay.termtime.config.time.TimeConfigurator;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.karakay.termtime.tools.Print.*;
import com.karakay.termtime.process.timer.TimerProcess;
import com.karakay.termtime.tools.TimeConvertor;

public class ArgsManager {
    List<String> args;

    public ArgsManager(List<String> args) {
        this.args = args;
    }

    public ArgsManager(String[] args) {
        this.args = Arrays.asList(args);
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    private boolean checkTimeArgs(List<String> args, int index) {
        char[] charArr = new char[] { 'h', 'm', 's' };
        for (int i = index; i < args.size(); i++) {
            for (int j = 0; j < charArr.length; j++) {
                if (args.get(i).contains(((Character) charArr[j]).toString()))
                    return true;
            }
        }
        return false;
    }

    private List<String> getTimerArgs(List<String> args, int index, boolean hasATimeCharacters) {
        if (hasATimeCharacters) {
            List<String> argList = new ArrayList<String>();
            for (int i = index; i < args.size(); i++) {
                argList.add(args.get(i));
            }
            return TimeConvertor.toBytesFromArgs(argList);
        } else {
            return Arrays.asList(
                    String.valueOf(
                            Instant.now().getEpochSecond()
                                    + Long.parseLong(
                                            args.get(index))));
        }
    }

    public void manage() {
        try {
            switch (args.get(0)) {
                case "--version":
                case "-version":
                case "version":
                case "-v": {
                    Greeting.showVersionCredentials();
                    break;
                }
                case "--help":
                case "-help":
                case "help":
                case "-h": {
                    HelpMessage.show();
                    break;
                }
                case "timer": {
                    switch (args.get(1)) {
                        case "infinity": {
                            try {
                                ProcessMaker.exec(
                                        TimerProcess.class,
                                        new ArrayList<String>(),
                                        Arrays.asList(
                                                String.valueOf(Integer.MAX_VALUE)));
                            } catch (Exception exception) {
                                printlnError("Incorrect value for the 'after' option!");
                                HelpMessage.show();
                            }
                            break;
                        }
                        case "after": {
                            try {
                                ProcessMaker.exec(
                                        TimerProcess.class,
                                        new ArrayList<String>(),
                                        getTimerArgs(args, 2, checkTimeArgs(args, 2)));
                            } catch (Exception exception) {
                                printlnError("Incorrect value for the 'after' option!");
                                HelpMessage.show();
                            }
                            break;
                        }
                        // TODO: create until property
                        case "--help":
                        case "-help":
                        case "help":
                        case "-h": {
                            HelpMessage.showTimerBlock();
                            break;
                        }
                        default: {
                            println("Incorrect attributes for the command!");
                            HelpMessage.show();
                        }
                    }
                    break;
                }

                case "config": {
                    switch (args.get(1)) {
                        case "getTimeZones": {
                            TimeConfigurator.getAllTimeZones()
                                    .forEach((k, v) -> System.out.printf(String.format("%-35s %s %n", k, v)));
                            break;
                        }
                        case "setTimeZone": {
                            TimeConfigurator timeConfigurator = new TimeConfigurator();
                            timeConfigurator.setTimeZone(args.get(2));
                            timeConfigurator.configure();
                            println(timeConfigurator.getConfig());
                            break;
                        }
                        case "setIcon": {
                            break;
                        }
                        case "setDefault": {
                            TimeConfigurator timeConfigurator = new TimeConfigurator();
                            timeConfigurator.configure();
                            println(timeConfigurator.getConfig());
                            break;
                        }
                        case "show": {
                            ConfigurationParser configParser = new ConfigurationParser();
                            println(configParser.getConfig());
                            break;
                        }
                        case "--help":
                        case "-help":
                        case "help":
                        case "-h": {
                            HelpMessage.showConfigBlock();
                            break;
                        }
                        default: {
                            println("Incorrect attributes for the command!");
                            HelpMessage.show();
                        }
                    }
                    break;
                }

                default: {
                    println("Incorrect attributes for the command!");
                    HelpMessage.show();
                }
            }
        } catch (ArrayIndexOutOfBoundsException outOfBoundsEx) {
            println("Incorrect attributes for the command!");
            HelpMessage.show();
        }
    }
}
