package main.java.test;

import main.java.entity.Blog;
import main.java.service.BlogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTemplateMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Blog blog = new Blog();
        blog.setName("熊老头");
        BlogService blogService = context.getBean("blogService", BlogService.class);
        blogService.add(blog);

        System.out.println(blogService.query(1));
    }
}
