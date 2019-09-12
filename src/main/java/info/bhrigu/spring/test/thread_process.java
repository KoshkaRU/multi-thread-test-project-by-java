package info.bhrigu.spring.test;

import java.util.concurrent.Callable;

import info.bhrigu.spring.test.beans.ResultHolder;
import info.bhrigu.spring.test.beans.SumProcessor;

public class thread_process extends Thread implements Callable<Boolean>, Runnable {

    static int index = 0;

    @Override
    public void run() {

        try {

            SumProcessor next = MainApp.processors.get(index++);

            if (next == null) {

                System.out.println("Нет процессора");

                System.exit(-1);

            }

            next.work();

            System.out.println("Thread " + this + " complete!");

        } catch (Exception e) {

            System.out.println("Error in thread " + this + ": " + e);

        }

    } //END: run()

    @Override
    public Boolean call() throws Exception {
        run();

        System.out.println(this.toString() + " complete task.");
        return true;
    }

};


