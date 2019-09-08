package info.bhrigu.spring.test.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ATest {

    @Test()
    void testA() throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(info.bhrigu.spring.test.myConfig.class);

        A testA = context.getBean(A.class);

        Assertions.assertThrows(Exception.class, () -> {testA.myB.method1();});

        context.close();

    } // END:

}