# Spring MVC

<h2>国际化</h2>
使用ModelAttribute注解，主要是定制用于不同页面对应不同语言的显示情况

1.在页面上能够根据浏览器语言设置的情况对文本，时间，数值进行本地化处理    
2.可以在bean中获取国际化资源文件locale对应的消息  
3.可以通过超链接切换locale，而不再依赖于浏览器的语言设置    
解决：     
1.使用JSTL的fmt标签  
2.在bean中注入ResourceBundleMessageSource的示例，使用其对应的getMessage方法即可   
3.配置LocalResolver和LocaleChangeInterceptor
<h2>数据绑定流程</h2>
1. Spring MVC主框架将ServletRequest对象及目标方法的入参实例传递给WebDataBinderFactory实例，以创建DataBinder实例对象
2. DataBinder调用装配在Spring MVC上下文中的ConversionService组件进行数据类型转换、数据格式化工作。将Servlet中的请求信息填充到入参对象中
3. 调用Validator组件对已经绑定了请求信息的入参对象进行数据合法性校验，并最终生成数据绑定结果BindingData对象
4. Spring MVC抽取BindingResult中的入参对象和校验错误对象，将它们赋给处理方法的响应入参