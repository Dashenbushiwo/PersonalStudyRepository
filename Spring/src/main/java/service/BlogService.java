package main.java.service;

import main.java.dao.BlogDao;
import main.java.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;

    public Blog query(Integer id) {
        return blogDao.selectOne(id);
    }

    public void add(Blog blog) {
        System.out.println("insert:" + blogDao.insert(blog));
    }

    /**
     * 声明式事务测试
     */
    @Transactional
    public void transactionalTest() {
        addDouble();
    }

    /**
     * 编程式事务
     */
    public void codeTransactionalTest(ApplicationContext context) {
        PlatformTransactionManager transactionManager = context.getBean("transactionManager", PlatformTransactionManager.class);
        // 获取一个事务
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            addDouble();
            transactionManager.commit(transaction);
        } catch (Exception e) {
            System.out.println("事务回滚，且程序继续执行");
            transactionManager.rollback(transaction);
        }
    }

    private void addDouble() {
        Blog blog = new Blog();
        blog.setName("aa");
        blogDao.insert(blog);

        boolean flag = "aa".equals(blog.getName());
        if (flag) {
            throw new RuntimeException("诶嘿");
        }

        blog.setName("bb");
        blogDao.insert(blog);
    }
}
