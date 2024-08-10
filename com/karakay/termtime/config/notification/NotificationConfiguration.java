package com.karakay.termtime.config.notification;

import java.io.Serializable;

public class NotificationConfiguration implements Serializable {
    public String messageIcon;

    public String toString() {
        return "messageIcon: " + this.messageIcon;
    }

    public NotificationConfiguration(String icon) {
        this.messageIcon = icon;
    }
}
