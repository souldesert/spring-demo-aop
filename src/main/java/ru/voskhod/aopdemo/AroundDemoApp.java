package ru.voskhod.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.voskhod.aopdemo.dao.AccountDAO;
import ru.voskhod.aopdemo.service.TrafficFortuneService;

import java.util.List;

public class AroundDemoApp {
    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        TrafficFortuneService fortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("Calling getFortune");

        String data = fortuneService.getFortune();

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");

        // close the context
        context.close();

    }
}
