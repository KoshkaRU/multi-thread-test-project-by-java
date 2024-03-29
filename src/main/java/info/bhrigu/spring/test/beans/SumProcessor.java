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

    public Long work() throws Exception {

        long t1 = System.currentTimeMillis();

        int i = 0;

        try {

            if (numbers == null) throw new Exception("Cannot receive array of numbers.");

            for (i = 0; i < numbers.size(); i++) {

                Long o = null;

                try {

                    o = numbers.get(i);

                    if (o == null) throw new Exception("no number");

                } catch (Exception e) {

                    final String s = "" +
                            "Error during of a number extraction from numbers: " +
                            e;

                    throw new Exception(s);

                }

                processor_sum += o;

            } // END: for

            if (sumHoldder == null) throw new Exception("No sum holder");

            synchronized (sumHoldder) {

                sumHoldder.setSum(sumHoldder.getSum() + processor_sum);

            }

            long t2 = System.currentTimeMillis();

            this.sumHoldder.total_time.addAndGet(t2 - t1);

        } catch (Exception e) {

            System.out.println("Work() error (" + i + ") " + e);

        }

        return processor_sum;

    } //END: work()

    @PostConstruct
    public void init() {

        System.out.println("Initializated B: " + this);

    } //END: init()

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