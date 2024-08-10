package com.karakay.termtime.process.timer;

import com.karakay.termtime.tools.Notificator;
import com.karakay.termtime.process.Process;

public class TimerProcess extends Process {
    public static void main(String[] args) {
        Notificator notificator = new Notificator();
        Timer timer;
        if (args.length == 1) {
            timer = new Timer(Long.parseLong(args[0]), notificator);
        } else {
            timer = new Timer(Byte.parseByte(args[0]),
                    Byte.parseByte(args[1]),
                    Byte.parseByte(args[2]),
                    notificator);
        }
        timer.run();
    }
}
