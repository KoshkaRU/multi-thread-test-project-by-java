package info.bhrigu.spring.test.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class B {

    public void method1() throws Exception {

        System.out.println("Method1 hello");

        if (Math.random() >= 0.5d) throw new IllegalArgumentException("Random!");

    } //END: method1

    @PostConstruct
    public void method2() {

        System.out.println("Initializated...");

    } //END: method2

    @PreDestroy
    public void method3() {

        System.out.println("Destroy...");

    } //END: method3

}
