package info.bhrigu.spring.test.beans;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class SumProcessor {

    private static int global_id = 0;
    final public int processor_id;
    private final ArrayList<Long> numbers;
    private Long processor_sum = 0l;

    @Autowired
    private final ResultHolder sumHoldder = null;

    public SumProcessor(ArrayList<Long> numbers) {

        this.numbers = numbers;

        processor_id = ++global_id;

    } // END: constructor

    public void work() throws Exception {

        long t1 = new java.util.Date().getTime();

        int i = 0;

        try {

            if (numbers == null) throw new Exception("Не удалось получить массив чисел.");
            for (i = 0; i < numbers.size(); i++) {
                Long o = null;
                try {
                    o = numbers.get(i);
                    if (o == null) throw new Exception("no number");
                } catch (Exception e) {
                    throw new Exception("Ошибка извлечения числа из массива: " + e);
                }
                processor_sum += o;
            } // END: for

            if (sumHoldder == null) throw new Exception("No sum holder");

            synchronized (sumHoldder) {
                sumHoldder.setSum(sumHoldder.getSum() + processor_sum);
            }

            long t2 = new java.util.Date().getTime();

            this.sumHoldder.total_time.addAndGet(t2 - t1);

        } catch (Exception e) {

            System.out.println("Work() error (" + i + ") " + e);

        }

        return;

    } //END: method1

    @PostConstruct
    public void init() {

        System.out.println("Initializated B: " + this);

    } //END: method2

    @PreDestroy
    public void destroy() {

        System.out.println("Destroy B: " + this);

    } //END: method3

    @Override
    public String toString() {
        return "" +
                "Processor " + processor_id + " " +
                "contain " + numbers.size() + " " +
                "numbers from " + numbers.get(0) +
                " to " + numbers.get(numbers.size() - 1);

    } //END: toString()

} //END: class SumProcessor