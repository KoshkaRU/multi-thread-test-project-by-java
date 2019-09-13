package info.bhrigu.spring.test;

import java.util.concurrent.Callable;

import info.bhrigu.spring.test.beans.SumProcessor;

public class thread_process extends Thread implements Callable<Long>, Runnable {

    static int index = 0;

    private Long sum = 0l;

    @Override
    public void run() {

        try {

            SumProcessor next = MainApp.processors.get(index++);

            if (next == null) {

                System.out.println("There is not processor in the list.");

                System.exit(-1);

            }

            this.sum = next.work();

        } catch (Exception e) {

            System.out.println("Error in thread " + this + ": " + e);

        } finally {

            //System.out.println("Thread "+index + ": " + this + " complete the work");

        }

    } //END: run()

    @Override
    public Long call() throws Exception {

        run();

        return sum;

    } //END: call()

}; //END: class thread_process


