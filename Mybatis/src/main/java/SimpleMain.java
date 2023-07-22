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
        // 获取SqlSessionFactory对象流程
        //      1. 创建SqlSessionFactoryBuilder对象
        //      2. SqlSessionFactoryBuilder对象将执行build方法，执行前先往下执行操作
        //      3. 创建解析器parser
        //      4. parser.parser解析每一个标签并把详细信息保存在Configuration
        //      5. parser.parser中同时会解析mapper.xml
        //      6. 返回Configuration对象
        //      7. SqlSessionFactoryBuilder.build(Configuration)
        //      8. 返回一个包含保存了全局配置信息的Configuration的DefaultSqlSessionFactory
        //      解析文件的每个信息保存在Configuration中，返回包含Configuration的DefaultSqlSessionFactory对象
        //      注意：mapper.xml中的每个元素信息解析出来并保存在全局配置信息中，
        //      且解析将增删改查的每一个标签每一个属性都解析出来封装成一个【MappedStatement】
        //      【MappedStatement】：一个该对象代表一个增删改查标签的详细信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 为什么使用SqlSessionManager，而不直接使用sqlSessionFactory.openSession？
        // SqlSessionManager是通过localSqlSession这个ThreadLocal变量，记录与当前线程绑定的SqlSession对象，
        // 供当前线程循环使用，从而避免在同一个线程多次创建SqlSession对象造成的性能损耗；
        // sqlSessionProxy则是用代理模式保证在执行sql操作时优先使用localSqlSession对象
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
        // openSession流程:
        //      1. openSessionFromDataSource
        //      2. 获取一些信息，创建tx（事务）
        //      3. configuration.newExecutor(tx, execType)
        //      4. 根据Executor在全局配置中的类型，创建出SimpleExecutor/ReuseExecutor/BatchExecutor
        //      5. 如果有二级缓存配置开启，创建CachingExecutor
        //      6. return (Executor) interceptorChain.pluginAll(executor);
        //         使用每一个拦截器重新包装executor并返回
        //      7. 创建并返回DefaultSqlSession-包含Configuration和Executor
        try (SqlSession session = sqlSessionManager.openSession()) {
            // getMapper(type)流程
            //      1. 调用 configuration.getMapper(type, this);
            //      2. 调用 mapperRegistry.getMapper(type, sqlSession);
            //      3. 根据接口类型获取MapperProxyFactory
            //      4. 创建MapperProxy对象，他是一个InvocationHandler
            //      5. 创建并返回MapperProxy的代理对象
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            // 增删改查流程
            //      1. 因mapper是MapperProxy的代理对象，故会调用MapperProxy.invoke方法
            //      2. 后续会调用到DefaultSqlSession.selectList
            //      3. 调用configuration.getMappedStatement(statement);拿到MappedStatement
            //      4. 调用executor.query方法
            //      5. 如二级缓存开启，则executor=CachingExecutor，
            //         会从二级缓存中取key，后判断缓存是否存在，存在则执行取缓存相关操作，否则继续执行BaseExecutor.query
            //      6. BaseExecutor.query会先取本地缓存（一级缓存），
            //         缓存不存在则再执行数据库查询BaseExecutor.queryFromDatabase方法
            //      7. BaseExecutor.queryFromDatabase方法中执行doQuery方法
            //      8. doQuery方法中通过configuration.newStatementHandler获取到StatementHandler对象
            //      9. 获取StatementHandler对象时
            //         根据MappedStatement中的statementType获取不同的StatementHandler类型，默认是预编译的
            //         同时会创建parameterHandler和resultSetHandler
            //         同时用均会调用interceptorChain.pluginAll(parameterHandler||resultSetHandler);
            //      10.在configuration.newStatementHandler中用拦截器包装StatementHandler
            //         (StatementHandler) interceptorChain.pluginAll(statementHandler);
            //      11.之后调用StatementHandler.parameterize，该方法中又会调用parameterHandler.setParameters
            //      12.调用typeHandler.setParameter(ps, i + 1, value, jdbcType);给sql语句预编译设置参数
            //      13.调用handler.query(stmt, resultHandler);执行查询
            //      14.最后调用resultSetHandler.handleResultSets(ps)处理返回结果
            //          其中会使用typeHandler获取value的值
            //      补充：TypeHandler：在整个过程中，进行数据库类型和javaBean类型的映射
            Blog blog = mapper.selectBlog(1);
            System.out.println(blog);
        }
    }

}
