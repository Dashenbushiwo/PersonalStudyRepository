# Mybatis
<h2>官方文档</h2>
https://mybatis.org/mybatis-3/zh/index.html

<h2>typeHandler</h2>
TypeHandler：在整个过程中，进行数据库类型和javaBean类型的映射；有必要的话可以自定义TypeHandler进行类型映射

<h2>插件开发</h2>

+ Mybatis在四大对象的创建过程中，都会有插件进行介入。
插件可利用动态代理机制一层层的包装目标对象，而实现在目标对象执行目标方法之前进行拦截的效果。
+ Mybatis允许在已映射语句执行过程中的某一点进行拦截调用。
+ 默认情况下，MyBatis允许使用插件来拦截的方法调用包括:
    + Executor(update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
    + ParameterHandler(getParameterObject, setParameters)
    + ResultSetHandler(handleResultSets, handleOutputParameters)
    + StatementHandler(prepare, parameterize, batch, update, query)

  插件机制上诉各个对象创建时都会调用interceptorChain.pluginAll(parameterHandler||resultSetHandler);

<h3>插件编写流程</h3>
1. 编写Interceptor
2. 使用@Intercepts完成插件签名:告诉Mybatis当前插件用来拦截哪个对象的哪个方法
3. 将写好的插件注册到全局配置文件中，mybatis-config中的plugins标签