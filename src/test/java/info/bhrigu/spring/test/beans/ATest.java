package info.bhrigu.spring.test.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ATest {

    @Test()
    void testA() throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(info.bhrigu.spring.test.myConfig.class);

        context.close();

    } // END:

}