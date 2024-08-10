package com.karakay.termtime;

import static com.karakay.termtime.tools.Print.*;
import java.io.IOException;
import java.util.List;
import com.karakay.termtime.process.timer.TimerProcess;
import java.util.ArrayList;
import java.io.File;

public class ProcessMaker {
    private static String javaHome = System.getProperty("java.home");
    private static String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
    private static String classpath = System.getProperty("java.class.path");

    public static void exec(Class<TimerProcess> cls, List<String> jvmArgs, List<String> clsArgs)
            throws IOException, InterruptedException {
        String className = cls.getName();

        List<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.addAll(jvmArgs);
        command.add("-cp");
        command.add(classpath);
        command.add(className);
        command.addAll(clsArgs);

        ProcessBuilder builder = new ProcessBuilder(command);
        builder
                .redirectInput(ProcessBuilder.Redirect.INHERIT)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT);
        Thread processThread = new Thread(() -> {
            try {
                builder.inheritIO().start();
            } catch (Exception exception) {
                printlnError(exception.getMessage());
            }
        });
        processThread.start();
    }
}
