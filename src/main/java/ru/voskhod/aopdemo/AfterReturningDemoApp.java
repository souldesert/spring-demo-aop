package ru.voskhod.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.voskhod.aopdemo.dao.AccountDAO;
import ru.voskhod.aopdemo.dao.MembershipDAO;

import java.util.List;

public class AfterReturningDemoApp {
    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        List<Account> accounts = accountDAO.findAccounts();

        // display the accounts
        System.out.println("\n\nMain Program: AfterReturningDemoApp");
        System.out.println("----------------------");

        System.out.println(accounts);
        System.out.println("\n");

        // close the context
        context.close();

    }
}
