package main.java.aop;

import org.springframework.stereotype.Component;

@Component
public class User {

    public void add() {
        System.out.println("User.add...");
    }

    public void throwing() {
        throw new RuntimeException("诶嘿");
    }
}
