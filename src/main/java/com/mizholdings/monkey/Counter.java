package com.mizholdings.monkey;

public class Counter {
    private static int index = 0;

    public static int getIndex() {
        return index;
    }

    public static void runIndex() {
        index++;
    }

    private Counter() {

    }

}
