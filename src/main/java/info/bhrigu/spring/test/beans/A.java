package info.bhrigu.spring.test.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class A {

    @Autowired
    public B myB;

}
