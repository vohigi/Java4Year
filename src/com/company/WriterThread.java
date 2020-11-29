package com.company;

import java.util.Queue;

public class WriterThread extends Thread{
    private final Queue<Integer> valueQueue;

    public WriterThread(Queue<Integer> valueQueue) {
        this.valueQueue = valueQueue;
    }
    @Override
    public void run() {
        int i = 1;
        while (i < 10) {
            synchronized (valueQueue) {
                System.out.format("Writing %d to queue\n", i);
                valueQueue.add(i);
                i++;
                PrintQueue();
                // Writer wakes up writer
                valueQueue.notify();

                // Writer waits until it can read
                try {
                    valueQueue.wait();
                } catch (InterruptedException ex) {
                    //
                }
            }
        }
    }
    private void PrintQueue(){
        System.out.print("Queue: ");
        for (int value:
             valueQueue) {
            System.out.print(value + ", ");
        }
        System.out.println();
    }
}
