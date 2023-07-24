package main.java.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * Intercepts：插件签名：告诉Mybatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts(
        {
                @Signature(
                        type = StatementHandler.class,
                        method = "parameterize",
                        args = java.sql.Statement.class
                )
        }
)
public class MyFirstInterceptor implements Interceptor {
    /**
     * 拦截目标对象的目标方法的执行
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstInterceptor.intercept:" + invocation.getMethod());
        // 执行目标方法
        Object proceed = invocation.proceed();
        System.out.println(proceed);
        // 返回执行后的返回值
        return proceed;
    }

    /**
     * plugin：包装目标对象的 包装：为目标对象创建一个代理对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstInterceptor.plugin:" + target);
        // 借助Plugin的wrap方法来使用当前Interceptor包装目标对象，
        // Plugin.wrap方法中会判断是否要包装对象，如果不需要包装直接返回对象，需要包装才返回代理对象
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MyFirstInterceptor.setProperties:" + properties);
        Interceptor.super.setProperties(properties);
    }
}
