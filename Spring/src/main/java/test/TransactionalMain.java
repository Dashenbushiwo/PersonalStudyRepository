package main.java.test;

import main.java.service.BlogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionalMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        BlogService blogService = context.getBean("blogService", BlogService.class);
        blogService.codeTransactionalTest(context);
        blogService.transactionalTest();
    }

}
