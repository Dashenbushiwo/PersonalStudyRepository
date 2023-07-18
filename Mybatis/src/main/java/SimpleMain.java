package main.java;

import main.java.entity.Blog;
import main.java.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1.创建者模式：创建了一个DefaultSqlSessionFactory，分离了解析配置文件以及创建对象的过程
 * 2.装饰者模式：SqlSessionManager用来包装原有的类DefaultSqlSessionFactory并在保持类方法签名完整性的前提下，提供了额外的功能
 */
public class SimpleMain {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 使用创建者模式创建了一个DefaultSqlSessionFactory，分离了解析配置文件以及创建对象的过程，
        // 配置文件包括但不仅仅是XMLConfigBuilder.parseConfiguration方法中的各种配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
        //
        try (SqlSession session = sqlSessionManager.openSession()) {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(1);
            System.out.println(blog);
        }
    }

}
