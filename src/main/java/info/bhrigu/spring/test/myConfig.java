package info.bhrigu.spring.test;

import info.bhrigu.spring.test.aspects.MethodLoggerBasic;
import info.bhrigu.spring.test.beans.A;
import info.bhrigu.spring.test.beans.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class myConfig {

    @Bean("A")
    public A getA() {
        return new A();
    }

    @Bean("B")
    public B getB() {
        return new B();
    }

    @Bean
    MethodLoggerBasic getlogger() {

        return new MethodLoggerBasic();

    }

}
