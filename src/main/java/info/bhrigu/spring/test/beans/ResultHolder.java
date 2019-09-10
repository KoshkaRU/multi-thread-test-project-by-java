package info.bhrigu.spring.test.beans;

import info.bhrigu.spring.test.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

@Component
@Scope("singleton")
public class ResultHolder {

    public static Integer sum = 0;

    public int getSum() {

        return sum;

    } // END: getSum()

    @PostConstruct
    public void init() {

        System.out.println("Init A: " + this.toString());

        // Form set of natural numbers from 1 to 100 (100 numbers)

        System.out.println("Starting form number set...");

        if (MainApp.numbers.size() != 10000) {

            for (int i = 1; i <= 10000; i++) {

                MainApp.numbers.add(Integer.valueOf(i));

            }

        }

        System.out.println("Numbers size: " + MainApp.numbers.size());

    } // END: init()

    public void setSum(int sum) {

        this.sum = sum;

    } // END: setSum()

    @PreDestroy
    void print_result() {

        System.out.println("Destroy A. Result of sum is: " + sum);
    }

} // ENDC: A