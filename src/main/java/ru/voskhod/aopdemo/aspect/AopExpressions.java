package ru.voskhod.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* ru.voskhod.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    // create a pointcut for getter methods

    @Pointcut("execution(* ru.voskhod.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // create a pointcut for setter methods

    @Pointcut("execution(* ru.voskhod.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // create a pointcut for ALL methods EXCEPT getters and setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
