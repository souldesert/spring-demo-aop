package ru.voskhod.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.voskhod.aopdemo.Account;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    
    private Logger logger = Logger.getLogger(getClass().getName());

    @Around("execution(* ru.voskhod.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print the method signature
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n=====>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute method
        Object result;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            // log the exception
            logger.warning(throwable.getMessage());

            // give user a custom message
            // result = "Major accident! But no worries, your private AOP helicopter is on the way!";

            throw throwable;
        }

        // compute duration and display it
        long end = System.currentTimeMillis();

        long duration = end - begin;

        // implicit type casting here
        logger.info("\n=====>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    // add new @After advice
    @After("execution(* ru.voskhod.aopdemo.dao.AccountDAO.find*(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>> Executing @After on method: " + method);
    }

    // add new @AfterThrowing advice
    @AfterThrowing(
            pointcut = "execution(* ru.voskhod.aopdemo.dao.AccountDAO.findAccountsWithException(..))",
            throwing = "e"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable e) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>> Executing @AfterThrowing on method: " + method);

        // log the exception
        logger.info("\n=====>> The exception is: " + e);
    }

    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* ru.voskhod.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        logger.info("\n=====>> The result is: " + result);

        // post-process data (modify it)

        // covert the account names to uppercase
        result.forEach(x -> x.setName(x.getName().toUpperCase()));

        // print modified result
        logger.info("\n=====>> The modified result is: " + result);
    }

    @Before("ru.voskhod.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        logger.info("\n=====>> Executing @Before advice before method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("Method: " + methodSignature);

        // display method arguments

        // get args and loop through them
        for (Object arg : joinPoint.getArgs()) {

            if (arg instanceof Account) {
                Account account = (Account) arg;
                logger.info(
                        String.format(
                                "Account: { name: %s, level: %s }",
                                account.getName(),
                                account.getLevel()
                        )
                );
            } else {
                logger.info("Is vip: " + arg);
            }
        }
    }

}
