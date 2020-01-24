package ru.voskhod.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.voskhod.aopdemo.service.TrafficFortuneService;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

    private static Logger logger = 
            Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

    public static void main(String[] args) {
               
        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        TrafficFortuneService fortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("Calling getFortune");

        String data = fortuneService.getFortune(true);

        logger.info("\nMy fortune is: " + data);

        logger.info("Finished");

        // close the context
        context.close();

    }
}
