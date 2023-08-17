package com.ps;

import com.ps.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootTest
class ApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;

    @Test
    public void contextLoads() {
        // 1. 测试@PropertySource和@ConfigurationProperties
        System.out.println(person);
        // 2. 测试@ImportResource
        Object dog = ioc.getBean("dog");
        System.out.println(dog);
    }

    @Test
    public void testLogs() {
        log.debug("打个日志玩一玩");
    }

}
