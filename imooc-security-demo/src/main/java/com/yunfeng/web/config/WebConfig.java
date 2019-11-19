package com.yunfeng.web.config;

import com.yunfeng.web.filter.TimeFilter;
import com.yunfeng.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-10
 */

//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 注册intercept
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //registry.addInterceptor(timeInterceptor);

    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.registerCallableInterceptors();
    }

    //@Bean
    //public FilterRegistrationBean timeFilter() {
    //    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    //
    //    TimeFilter filter = new TimeFilter();
    //    registrationBean.setFilter(filter);
    //
    //    List<String> urls = new ArrayList<>();
    //    urls.add("/*");
    //    registrationBean.setUrlPatterns(urls);
    //    return registrationBean;
    //
    //}
}
