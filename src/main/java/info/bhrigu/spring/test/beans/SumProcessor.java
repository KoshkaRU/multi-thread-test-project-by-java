package info.bhrigu.spring.test.beans;

import info.bhrigu.spring.test.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class SumProcessor {


    @Autowired
    private final ResultHolder sumHoldder = null;

    private final int start;

    private final int stop;

    /**
     * Constructor
     *
     * @param start Start number
     * @param stop  End number
     */
    public SumProcessor(int start, int stop) {

        this.start = start;

        this.stop = stop;

    } // END: constructor

    int total_sum = 0;

    public void work() throws Exception {

        for (int i = start; i <= stop; i++) {

            System.out.println("processing " + i);

            synchronized ((MainApp.numbers)) {

                Integer o = null;

                try {

                    o = MainApp.numbers.iterator().next();

                    MainApp.numbers.remove(o);

                } catch (Exception e) {

                    throw new Exception("Error of numbers: " + e);

                }

                total_sum += o;

            } // END: synchronized

        } // END: for

        synchronized (sumHoldder) {

            sumHoldder.setSum(sumHoldder.getSum() + total_sum);

        }

        return;

    } //END: method1

    @PostConstruct
    public void init() {

        System.out.println("Initializated B: " + this.toString());

    } //END: method2

    @PreDestroy
    public void destroy() {

        System.out.println("Destroy B: " + this.toString());

    } //END: method3

}
