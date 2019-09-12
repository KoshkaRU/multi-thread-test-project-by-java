package info.bhrigu.spring.test.beans;

import info.bhrigu.spring.test.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

import static info.bhrigu.spring.test.MainApp.SIZE;

@Component
@Scope("singleton")
public class ResultHolder {

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


    void print_result() {

        System.out.println("Destroy A");

    }

    public void printSum() {
        System.out.println("Sum is " + sum);
    }

    public void clearSum() {
        sum = 0l;
    }

} // ENDC: A