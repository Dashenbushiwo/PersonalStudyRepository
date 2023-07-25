package main.java.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
// @Order(1) 当多个类对同一方法进行增强时可通过该注解设置优先级，数字越小优先级越高
public class UserProxy {

    @Before("execution(* main.java.aop.User.add(..))")
    public void before() {
        System.out.println("UserProxy.before...");
    }

    @After("execution(* main.java.aop.User.add(..))")
    public void after() {
        System.out.println("UserProxy.after...");
    }

    @Around("execution(* main.java.aop.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("UserProxy.around-before...");
        proceedingJoinPoint.proceed();
        System.out.println("UserProxy.around-after...");
    }

    @AfterThrowing("execution(* main.java.aop.User.throwing(..))")
    public void afterThrowing() {
        System.out.println("UserProxy.afterThrowing...");
    }

    @AfterReturning("execution(* main.java.aop.User.add(..))")
    public void afterReturning() {
        System.out.println("UserProxy.afterReturning...");
    }
}
