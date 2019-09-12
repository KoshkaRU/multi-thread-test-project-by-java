package info.bhrigu.spring.test;

import info.bhrigu.spring.test.beans.ResultHolder;
import info.bhrigu.spring.test.beans.SumProcessor;
import org.springframework.context.annotation.*;

import java.util.*;
import java.util.concurrent.*;

public class MainApp {

    public static long time = 0;

    public static int SIZE = 10_000_000;

    public final static int DIVIDE_FACTOR = 4;

    public static ArrayList<Long>[] numbers = new ArrayList[DIVIDE_FACTOR];

    static AnnotationConfigApplicationContext context;

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

            if (bean == null) throw new Exception("Не удалось достать SumProcessor.class");

            processors.add(bean);

        }

        // creates 4 threads fro processors

        thread_process thread1 = new thread_process();
        thread_process thread2 = new thread_process();
        thread_process thread3 = new thread_process();
        thread_process thread4 = new thread_process();

        ResultHolder a = context.getBean(ResultHolder.class);

        try {

            boolean isByThread = true; // flag

            time = 0;

            System.out.println("--------------------");
            System.out.println("Многопоточный расчёт");
            System.out.println("--------------------");

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

            System.out.println("Time is: " + time);

            a.printSum();

            a.clearSum();

            time = 0;

            System.out.println("---------------------------");
            System.out.println("Одиночный поток расчитывает");
            System.out.println("---------------------------");

            ArrayList<Long> numbers_tolal = new ArrayList<>();

            for (i = 0; i < SIZE; i++) {

                numbers_tolal.add((i + 1l));

            }

            SumProcessor sumProcessor = context.getBean(SumProcessor.class, numbers_tolal);

            sumProcessor.work();

            System.out.println("Time is: " + time);

            a.printSum();

        } catch (Exception e) {

            throw new Exception("Work error: " + e);

        }

        context.close();

    } // END: main

} // END: class MainApp