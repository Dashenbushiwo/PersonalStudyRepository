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
 * 插件原理
 * 在四大对象创建的时候
 * 1. 每个创建出来的对象不是直接返回的，而是
 *      interceptorChain.pluginAll(parameterHandler);
 *        public Object pluginAll(Object target) {
 *          for (Interceptor interceptor : interceptors) {
 *              target = interceptor.plugin(target);
 *        }
 *     return target;
 *   }
 * 2. 获取到所有的Interceptor（拦截器）（插件需要实现的接口）
 *      调用interceptorChain.plugin(target);放回target包装后的对象
 * 3. 插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）
 *      我们的插件可以为四大对象创建出代理对象
 *      代理对象就可以拦截到四大对象的每个执行
 */
public class PluginMain {

    /**
     * 插件编写
     * 1. 编写Interceptor
     * 2. 使用@Intercepts完成插件签名:告诉Mybatis当前插件用来拦截哪个对象的哪个方法
     * 3. 将写好的插件注册到全局配置文件中，mybatis-config中的plugins标签
     */
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
        try (SqlSession session = sqlSessionManager.openSession()) {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(1, null);
            System.out.println(blog);
        }
    }

}
