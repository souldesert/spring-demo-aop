package ru.voskhod.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.voskhod.aopdemo.Account;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* ru.voskhod.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println(method);
        System.out.println("\n=====>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>> The result is: " + result);
    }

    @Before("ru.voskhod.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=====>> Executing @Before advice before method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args and loop through them
        for (Object arg : joinPoint.getArgs()) {

            if (arg instanceof Account) {
                Account account = (Account) arg;
                System.out.println(
                        String.format(
                                "Account: { name: %s, level: %s }",
                                account.getName(),
                                account.getLevel()
                        )
                );
            } else {
                System.out.println("Is vip: " + arg);
            }
        }
    }

}
