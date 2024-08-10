package com.karakay.termtime;

/**
 * term-time utility
 * Sets the timer from now until the time specified in the arguments
 * 
 * @author Vladyslav Karakai
 * @author github.com/Karakay-V
 * @version 1.0.0
 */

public class TermTime {
    public static void main(String[] args) {
        Thread.currentThread().setName("TermTime-main");
        new ArgsManager(args).manage();
    }
}
