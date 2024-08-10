package com.karakay.termtime.tools;

public class Print {
    // *** System.out.print ***
    // * stdout METHODS *
    public static void print(Object obj) {
        System.out.print(obj);
    }
    public static void print(String str) {
        System.out.print(str);
    }

    // *** System.out.println ***
    // * stdout METHODS *
    public static void println(Object obj) {
        System.out.println(obj);
    }
    public static void println(String str) {
        System.out.println(str);
    }
    public static void lineBreak() {
        System.out.println();
    }

    // *** System.err.print ***
    // * stderr METHODS *
    public static void printError(Object obj) {
        System.err.print(obj);
    }
    public static void printError(String str) {
        System.err.print(str);
    }

    // *** System.err.println ***
    // * stderr METHODS *
    public static void printlnError(Object obj) {
        System.err.println(obj);
    }
    public static void printlnError(String str) {
        System.err.println(str);
    }
}