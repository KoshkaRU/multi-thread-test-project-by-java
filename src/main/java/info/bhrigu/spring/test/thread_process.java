package info.bhrigu.spring.test;

import info.bhrigu.spring.test.beans.SumProcessor;

import java.util.concurrent.Callable;

public class thread_process extends Thread implements Callable<Boolean> {

    static int index = 0;

    @Override
    public void run() {

        try {

            SumProcessor next = MainApp.processors.get(index++);

            if (next == null) {

                System.out.println("Нет процессора");

                System.exit(-1);

            } ;

            next.work();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public Boolean call() throws Exception {
        run();

        System.out.println(this.toString() + " complete task.");
        return true;
    }

};


