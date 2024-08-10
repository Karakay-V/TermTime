package com.karakay.termtime;

import static com.karakay.termtime.tools.Print.println;

public class Greeting {
    public final static String VERSION = "1.0.0";
    public final static String LOGO = "  _______               ________                \r\n |__   __|             |__   ___|               \r\n    | | ___ _ __ _ __ ___ | |   _ _ __ ___   ___ \r\n    | |/ _ \\ '__| '_ ` _ \\| |  | | '_ ` _ \\ / _ \\\r\n    | |  __/ |  | | | | | | |  | | | | | | |  __/\r\n    |_|\\___|_|  |_| |_| |_|_|  |_|_| |_| |_|\\___|\r\n";

    public static void showVersionCredentials() {
        println(LOGO);
        println("App version is: " + VERSION);
        println("TermTime is a terminal utility developed by me while studying Java SE 8.");
        println("You could follow me on 'https://github.com/Karakay-V/'");
    }
}