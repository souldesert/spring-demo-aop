package ru.voskhod.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* ru.voskhod.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // create a pointcut for getter methods

    @Pointcut("execution(* ru.voskhod.aopdemo.dao.*.get*(..))")
    private void getter() {}

    // create a pointcut for setter methods

    @Pointcut("execution(* ru.voskhod.aopdemo.dao.*.set*(..))")
    private void setter() {}

    // create a pointcut for ALL methods EXCEPT getters and setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}

    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>> Executing @Before advice before method");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n=====>> Performing API analytics...");
    }
}
