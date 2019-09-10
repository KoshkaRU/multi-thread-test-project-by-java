package info.bhrigu.spring.test.aspects;

import info.bhrigu.spring.test.MainApp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
public class MethodLoggerBasic {

    @Pointcut("execution(* info.bhrigu.spring.test.beans.SumProcessor.*(..))")
    void around_work() {};

    @Around("around_work()")
    public void logMethodName(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Method name is:"+joinPoint.getSignature());

        long starttime = new Date().getTime();

        joinPoint.proceed();

        long endtime = new Date().getTime();

        long time = endtime - starttime;

        MainApp.time += time;

    } // END:

}
