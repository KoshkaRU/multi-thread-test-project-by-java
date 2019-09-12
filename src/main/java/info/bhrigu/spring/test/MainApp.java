package info.bhrigu.spring.test;

import info.bhrigu.spring.test.beans.SumProcessor;
import org.springframework.context.annotation.*;

import java.util.*;
import java.util.concurrent.*;

public class MainApp {

    public static long time = 0;

    public static int SIZE = 1_000_000;

    public static Vector<Integer> numbers = new Vector<>();

    static AnnotationConfigApplicationContext context;

    public static Set<SumProcessor> processors = new HashSet<>();

    public static void main(String[] args) throws Exception {

        context = new AnnotationConfigApplicationContext(myConfig.class);

        int DIVIDE_FACTOR = 4;

        int part_size = SIZE / DIVIDE_FACTOR;

        for (int i = 0; i < DIVIDE_FACTOR; i++) {

            int start_i =  (i * part_size) + (1);

            int end_i = (i + 1) * part_size;

            System.out.println("Start: " +
                    start_i +
                    ", End: " +
                    end_i);

            processors.add(context.getBean(SumProcessor.class, start_i, end_i));

        }

        thread_process thread1 = new thread_process();
        thread_process thread2 = new thread_process();
        thread_process thread3 = new thread_process();
        thread_process thread4 = new thread_process();

        try {

            boolean isByThread = false; // flag

            if (isByThread) {

                ExecutorService pool = new ThreadPoolExecutor(
                        4,
                        4,
                        0,
                        TimeUnit.MICROSECONDS,
                        new ArrayBlockingQueue<Runnable>(4)
                );

                List<Callable<Boolean>> tasks = new ArrayList();

                tasks.add(thread1);
                tasks.add(thread2);
                tasks.add(thread3);
                tasks.add(thread4);

                List<Future<Boolean>> futures = pool.invokeAll(tasks);

                pool.shutdown();

            } else {

                for (SumProcessor p: MainApp.processors
                     ) {

                    p.work();

                }


            }

        } catch (Exception e) {

            throw new Exception("Work error: " + e);

        }

        context.close();

        System.out.println("Time is: " + time);

    } // END: main

} // END: class MainApp

class thread_process extends Thread implements Callable<Boolean> {

    @Override
    public void run() {
        try {
            SumProcessor next = MainApp.processors.iterator().next();
            next.work();
            MainApp.processors.remove(next);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public Boolean call() throws Exception {
        run();
        return true;
    }

};
