package main.java.test;

import main.java.entity.Blog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreatObjMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Blog blog1 = context.getBean("blog", Blog.class);
        System.out.println(blog1);
        blog1.setName("诶嘿");
        Blog blog2 = context.getBean("blog", Blog.class);
        System.out.println(blog2);
    }

}
