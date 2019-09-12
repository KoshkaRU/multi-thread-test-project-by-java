package info.bhrigu.spring.test;

import info.bhrigu.spring.test.aspects.MethodLoggerBasic;
import info.bhrigu.spring.test.beans.SumProcessor;
import org.springframework.context.annotation.*;

import java.util.ArrayList;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("info.bhrigu.spring.test.beans")
public class myConfig {

    @Bean
    MethodLoggerBasic getlogger() {

        return new MethodLoggerBasic();

    }

    @Scope("prototype")
    @Bean
    SumProcessor getProcessor(ArrayList<Long> numbers) {

        return new SumProcessor(numbers);

    }

}
