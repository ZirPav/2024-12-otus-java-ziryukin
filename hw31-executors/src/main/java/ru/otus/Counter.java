package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Counter {

    private static final Logger logger = LoggerFactory.getLogger(Counter.class);
    private int prev = 1;
    private int count = 1;
    private String sleepingThread = "Поток2";
    private String operation = "plus";

    private synchronized void action() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                // поэтому не if
                while (sleepingThread.equals(Thread.currentThread().getName())) {
                    this.wait();
                }
                if ("Поток2".equals(Thread.currentThread().getName())) {
                    logger.info(String.valueOf(prev));
                } else {
                    prev = count;
                    logger.info(String.valueOf(count));
                    if (count == 10) {
                        operation = "minus";
                    }
                    if (count == 1) {
                        operation = "plus";
                    }
                    if ("plus".equals(operation)) {
                        count = count + 1;
                    } else {
                        count = count - 1;
                    }
                }
                sleepingThread = Thread.currentThread().getName();
                sleep();
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        final Counter counter = new Counter();
        final Thread thread1 = new Thread(counter::action);
        thread1.setName("Поток1");
        thread1.start();
        final Thread thread2 = new Thread(counter::action);
        thread2.setName("Поток2");
        thread2.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
