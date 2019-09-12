package info.bhrigu.spring.test.aspects;

import info.bhrigu.spring.test.MainApp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

@Aspect
public class MethodLoggerBasic {

    //@Pointcut("execution(* info.bhrigu.spring.test.beans.SumProcessor.work(..))")
    @Pointcut("execution(* *.work(..))")
    void around_work() {};

    @Around("around_work()")
    public void logMethodName(ProceedingJoinPoint joinPoint) throws Throwable {

        long starttime = new Date().getTime();
        joinPoint.proceed();
        long endtime = new Date().getTime();
        long time = endtime - starttime;
        MainApp.time += time;

    } // END:
} // ENDC
