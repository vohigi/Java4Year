package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Queue <Integer> values = new LinkedList<>();
        Thread write = new WriterThread(values);
        Thread read = new ReaderThread(values);
        read.start();
        write.start();
        try {
            write.join();
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
