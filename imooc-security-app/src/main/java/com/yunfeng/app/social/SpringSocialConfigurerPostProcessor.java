package com.yunfeng.app.social;

import com.yunfeng.security.core.social.ImoocSpringSocialConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
 
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
 
 
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (StringUtils.equals(beanName, "imoocSocialSecurityConfig")) {
		    ImoocSpringSocialConfigurer configurer = (ImoocSpringSocialConfigurer)bean;
			configurer.signupUrl("/social/signUp");//更换浏览器的默认注册
			return configurer;
		}
		return bean;
	}
}
