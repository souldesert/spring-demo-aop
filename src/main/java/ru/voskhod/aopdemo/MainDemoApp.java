package ru.voskhod.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.voskhod.aopdemo.dao.AccountDAO;
import ru.voskhod.aopdemo.dao.MembershipDAO;

public class MainDemoApp {
    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // get membership bean from the spring container
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // call the business method
        Account account = new Account();
        account.setName("Madhu");
        account.setLevel("Platinum");

        accountDAO.addAccount(account, true);
        accountDAO.doWork();

        // call the AccountDAO getter/setter methods
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

        String name = accountDAO.getName();
        String code = accountDAO.getServiceCode();

        // call membership business method
        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        // close the context
        context.close();

    }
}
