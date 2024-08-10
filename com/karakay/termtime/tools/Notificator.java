package com.karakay.termtime.tools;

import java.util.ArrayList;
import java.util.List;
import static com.karakay.termtime.tools.Print.*;

public class Notificator {
    private static String NOTIFY_SEND_BIN = "notify-send";
    private static String APP_NAME_ATTR = "--app-name";
    private static String URGENCY_ATTR = "--urgency";
    private static String EXPIRE_TIME_ATTR = "--expire-time";
    private static String ICON_ATTR = "--icon";
    private static String PRINT_ID_ATTR = "--print-id";
    private static String WAIT_ATTR = "--wait";

    public void sendNotification(String title, String body) {
        List<String> command = new ArrayList<String>();
        command.add(NOTIFY_SEND_BIN);
        addDefaultAttrs(command);
        addTitleAndBody(command, title, body);
        lineBreak();
        if (makeNotification(command) != 0) {
            lineBreak();
            println(title);
            println(body);
        }
    }

    public void sendNotification(String title, String body, NotificationUrgency urgency, int expireTime,
            String iconPath) {
        List<String> command = new ArrayList<String>();
        command.add(NOTIFY_SEND_BIN);
        command.add(URGENCY_ATTR);
        command.add(urgency.toString().toLowerCase());
        command.add(EXPIRE_TIME_ATTR);
        command.add(((Integer) expireTime).toString());
        command.add(ICON_ATTR);
        command.add(iconPath);
        addTitleAndBody(command, title, body);
        lineBreak();
        if (makeNotification(command) != 0) {
            lineBreak();
            println(title);
            println(body);
        }
    }

    private void addDefaultAttrs(List<String> command) {
        command.add(URGENCY_ATTR);
        command.add(NotificationUrgency.NORMAL.toString().toLowerCase());
        command.add(APP_NAME_ATTR);
        command.add("term-time");
        command.add(ICON_ATTR);
        command.add("/home/karakay-v/Downloads/timer-notification-icon-64px.png");
    }

    private void addTitleAndBody(List<String> command, String title, String body) {
        command.add(title);
        command.add(body);
    }

    private int makeNotification(List<String> command) {
        try {
            Process process = new ProcessBuilder(command).inheritIO().start();
            process.waitFor();
            return process.exitValue();
        } catch (Exception exception) {
            printlnError(exception.getMessage());
        }
        return 1;
    }

    // Requirment packages: "libnotify"
    // TODO: notify-send --urgency critical --expire-time 7000 --icon
    // ~/.config/path-to-icon-256px.png "Title of test notification" "Text body of
    // test notification"
}
