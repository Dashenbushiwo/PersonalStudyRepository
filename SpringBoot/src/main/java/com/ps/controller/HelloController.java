package com.ps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/hello/")
@Controller
//@RestController("/hello/")
public class HelloController {

    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        return "Hello SpringBoot";
    }

    @RequestMapping("helloThymeleaf")
    public String helloThymeleaf(Map<String, Object> map) {
        // 只要把页面放在classpath:/templates/下，thymeleaf就会自动渲染
        // thymeleaf- classpath:/templates/index.html
        map.put("hello", "你好，thymeleaf");
        return "index";
    }

}
