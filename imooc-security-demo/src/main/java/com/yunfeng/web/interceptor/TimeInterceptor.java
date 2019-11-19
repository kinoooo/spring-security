package com.yunfeng.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-10
 */

@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * controller 某个方法被调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("startTime", new Date().getTime());

        return true;
    }

    /**
     * 控制器方法被执行的时候调用，抛出异常是不会执行的
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler");
        Long start = (Long)request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
    }

    /**
     * 完成都会被调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
        System.out.println("ex is " + ex);
    }
}
