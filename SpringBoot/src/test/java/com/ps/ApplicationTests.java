package com.ps;

import com.ps.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;

    @Test
    void contextLoads() {
        // 1. 测试@PropertySource和@ConfigurationProperties
        System.out.println(person);
        // 2. 测试@ImportResource
        Object dog = ioc.getBean("dog");
        System.out.println(dog);
    }

}
