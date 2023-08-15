package com.ps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/hello/")
public class HelloController {

    @RequestMapping("hello")
    public String hello() {
        return "Hello SpringBoot";
    }
}
