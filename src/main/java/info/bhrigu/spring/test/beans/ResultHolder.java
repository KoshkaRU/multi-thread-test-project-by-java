package info.bhrigu.spring.test.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import info.bhrigu.spring.test.MainApp;

@Component
@Scope("singleton")

public class ResultHolder {

    public static AtomicLong total_time = new AtomicLong(0);

    public static Long sum = 0l;

    public Long getSum() {

        return sum;

    } // END: getSum()

    @PostConstruct
    public void init() {

    } // END: init()

    public void setSum(Long sum) {

        this.sum = sum;

    } // END: setSum()

    public void printSum() {
        System.out.println("Sum is " + sum);
    }

    public void clearSum() {
        sum = 0l;
    }

} // ENDC: ResultHolder