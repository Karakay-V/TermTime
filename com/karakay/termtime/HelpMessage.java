package com.karakay.termtime;

import static com.karakay.termtime.tools.Print.*;

public class HelpMessage {
    public static void show() {
        showHelpBlock();
        showVersionBlock();
        showTimerBlock();
        showConfigBlock();
        showExampleBlock();
    }

    public static void showHelpBlock() {
        println("Print this help message.");
        println("   [help | -help | --help | -h]");
        lineBreak();
    }

    public static void showVersionBlock() {
        println("Print the version of app and author credentials.");
        println("   [version | -version | --version | -v]");
        lineBreak();
    }

    public static void showTimerBlock() {
        println("Create new timer from now.");
        println("   [timer]");
        println("   After specified time in seconds.");
        println("         [after] (seconds)");
        println("   Also you can specify time with 'h' (for hours), 'm' (for minutes) and 's' (for seconds) keys.");
        println("         [after] (0h | 0m | 0s)");
        lineBreak();
    }

    public static void showConfigBlock() {
        println("Configure app with.");
        println("   [config]");
        println("   Print the current configuration.");
        println("         [show]");
        lineBreak();
        println("   Get all time zones and it difference from GMT.");
        println("         [getTimeZones]");
        lineBreak();
        println("   Set your time zone or difference from GMT from any time zone present to it.");
        println("         [setTimeZone] (timeZone | timeDifference)");
        lineBreak();
        println("   Set the default time zone.");
        println("         [setDefault]");
        lineBreak();
    }

    public static void showExampleBlock() {
        println("Examples of usage:");
        lineBreak();
        println("Show me your version!");
        println("   $ term-time version");
        lineBreak();
        println("Show me your short documentation!");
        println("   $ term-time help");
        lineBreak();
        println("Set the timer for 5 minutes!");
        println("   $ term-time timer after 300");
        lineBreak();
        println("Or set the timer for 5 minutes like this!");
        println("   $ term-time timer after 5m");
        lineBreak();
    }
}
