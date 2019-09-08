package info.bhrigu.spring.test.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class MethodLoggerBasic {

    @Pointcut("execution(* info.bhrigu.spring.test.beans.B.method*(..))")
    void beforeallmethod() {};

    @Around("beforeallmethod()")
    public void logMethodName(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Method name is:"+joinPoint.getSignature());
        joinPoint.proceed();

    }

}
