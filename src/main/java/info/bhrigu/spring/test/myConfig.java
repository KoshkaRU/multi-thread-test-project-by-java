package info.bhrigu.spring.test;

import info.bhrigu.spring.test.aspects.MethodLoggerBasic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("info.bhrigu.spring.test.beans")
public class myConfig {

    @Bean
    MethodLoggerBasic getlogger() {

        return new MethodLoggerBasic();

    }

}
