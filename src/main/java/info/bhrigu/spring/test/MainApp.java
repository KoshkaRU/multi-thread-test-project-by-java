package info.bhrigu.spring.test;

import info.bhrigu.spring.test.beans.SumProcessor;
import org.springframework.context.annotation.*;

import java.util.HashSet;
import java.util.Set;

public class MainApp {

    public static long time = 0;

    public static Object dummy = new Object();

    public static Set<Integer> numbers = new HashSet<Integer>();

    static AnnotationConfigApplicationContext context;

    public static void main(String[] args) throws Exception {

        context = new AnnotationConfigApplicationContext(myConfig.class);

        SumProcessor sumProcessor1 = context.getBean(SumProcessor.class, 1, 2500);
        SumProcessor sumProcessor2 = context.getBean(SumProcessor.class, 2501, 5000);
        SumProcessor sumProcessor3 = context.getBean(SumProcessor.class, 5001, 7500);
        SumProcessor sumProcessor4 = context.getBean(SumProcessor.class, 7501, 10000);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    sumProcessor1.work();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    System.exit(-1);
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    sumProcessor2.work();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    System.exit(-1);
                }
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                try {
                    sumProcessor3.work();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    System.exit(-1);
                }
            }
        };

        Thread thread4 = new Thread() {
            @Override
            public void run() {
                try {
                    sumProcessor4.work();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    System.exit(-1);
                }
            }
        };

        try {

            if (false) {

                thread1.start();

                thread2.start();

                thread3.start();

                thread4.start();

            } else {

                sumProcessor1.work();

                sumProcessor2.work();

                sumProcessor3.work();

                sumProcessor4.work();

            }

        } catch (Exception e) {

            throw new Exception("Work error: " + e);

        }

        context.close();

        System.out.println("Time is: " + time);

    } // END: main

} // END: class MainApp
