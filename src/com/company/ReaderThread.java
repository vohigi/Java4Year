package com.company;

import java.util.Queue;

public class ReaderThread extends Thread{
    private final Queue<Integer> valueQueue;

    public ReaderThread(Queue<Integer> valueQueue) {
        this.valueQueue = valueQueue;
    }

    @Override
    public void run() {
        synchronized (valueQueue) {
            // Reader waits for the first time!
            try {
                valueQueue.wait();
            } catch (InterruptedException ex) {
                //
            }
        }
        int i = 1;
        while (i < 10) {
            synchronized (valueQueue) {

                System.out.format("Queue average: %f\n", Average(valueQueue));
                i++;

                // Reader wakes up writer
                valueQueue.notify();

                // If there are still more values to read
                if (i < 100) {
                    // Reader waits until it can read
                    try {
                        valueQueue.wait();
                    } catch (InterruptedException ex) {
                        //
                    }
                }
            }
        }
    }
    private double Average(Queue<Integer> queue){
        double sum = 0;
        for (int value:valueQueue) {
            sum+=value;
        }
        return sum / queue.size();
    }
}
