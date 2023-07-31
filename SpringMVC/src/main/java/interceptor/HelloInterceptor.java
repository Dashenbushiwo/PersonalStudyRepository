package main.java.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloInterceptor implements HandlerInterceptor {

    /**
     * 该方法在目标方法之前被调用
     * 若返回值为true，则继续调用后续的拦截器和目标方法
     * 若返回值为false，则不会再调用后续的拦截器和目标方法
     *
     * 可考虑做权限、日志、事务等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 调用目标方法之后，但渲染视图之前
     *
     * 可以对请求域中的属性或视图做出修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("HelloInterceptor.postHandle");
    }

    /**
     * 渲染视图方法后调用
     *
     * 释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("HelloInterceptor.afterCompletion");
    }
}
