package info.bhrigu.spring.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import info.bhrigu.spring.test.beans.ResultHolder;
import info.bhrigu.spring.test.beans.SumProcessor;

public class MainApp {

    static AnnotationConfigApplicationContext context;

    public static long time = 0l;
    public final static int SIZE = 40_000_000;
    public final static int DIVIDE_FACTOR = 4;
    public static ArrayList<Long>[] numbers = new ArrayList[DIVIDE_FACTOR];

    public static ArrayList<SumProcessor> processors = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        context = new AnnotationConfigApplicationContext(myConfig.class);

        // form 4 datasets

        int part_size = SIZE / DIVIDE_FACTOR;

        int i;
        int j;

        for (j = 0; j < DIVIDE_FACTOR; j++) {
            numbers[j] = new ArrayList<>();
            for (i = 0; i < (int) part_size; i++) {
                numbers[j].add(((j * part_size) + i + 1l));
            }
        }

        // create 4 processors (bean)

        for (i = 0; i < DIVIDE_FACTOR; i++) {

            SumProcessor bean = context.getBean(SumProcessor.class, numbers[i]);

            if (bean == null) throw new Exception("Error recive bean SumProcessor.class");
            processors.add(bean);
        }

        // creates 4 threads fro processors
        thread_process thread1 = new thread_process();
        thread_process thread2 = new thread_process();
        thread_process thread3 = new thread_process();
        thread_process thread4 = new thread_process();

        ResultHolder a;

        a = context.getBean(ResultHolder.class);

        try {

            boolean isByPool = true; // flag

            time = 0;

            if (isByPool) {

                System.out.println("-------------------");
                System.out.println("Multithread compute");
                System.out.println("-------------------");
                ExecutorService pool = new ThreadPoolExecutor(
                        4,
                        4,
                        0,
                        TimeUnit.MICROSECONDS,
                        new ArrayBlockingQueue<>(4)
                );

                List<Callable<Boolean>> tasks = new ArrayList();

                tasks.add(thread1);
                tasks.add(thread2);
                tasks.add(thread3);
                tasks.add(thread4);
                pool.invokeAll(tasks);

                pool.shutdown();
                pool.awaitTermination(60, TimeUnit.SECONDS);

            } else {

                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();

                thread1.join();
                thread2.join();
                thread3.join();
                thread4.join();

            }

            a.printSum();
            a.clearSum();

            System.out.println("total time is " + a.total_time);
            System.out.println("basic time is " + MainApp.time);

            System.out.println("-------------");
            System.out.println("Single thread");
            System.out.println("-------------");

            ArrayList<Long> numbers_tolal = new ArrayList<>();

            for (i = 0; i < SIZE; i++) {
                numbers_tolal.add((i + 1l));
            }

            SumProcessor sumProcessor = context.getBean(SumProcessor.class, numbers_tolal);

            a.total_time.set(0l);
            time = 0l;

            sumProcessor.work();

            a.printSum();

            System.out.println("total time is " + a.total_time);
            System.out.println("basic time is " + MainApp.time);

        } catch (Exception e) {

            throw new Exception("MainApp error: " + e);

        }

        context.close();

    } // END: main

} // END: class MainApp