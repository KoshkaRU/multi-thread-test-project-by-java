package info.bhrigu.spring.test;

import info.bhrigu.spring.test.beans.A;
import info.bhrigu.spring.test.myConfig;
import org.springframework.context.annotation.*;

public class MainApp {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(myConfig.class);

        A myA = context.getBean(A.class);

        try {
            myA.myB.method1();
        } catch (Exception e) {
            throw new Exception("myB.method1(): "+e);
        }

        context.close();

    } // END: main

} // END: class MainApp
