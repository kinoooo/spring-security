package com.yunfeng.app.config;

import com.yunfeng.app.authentication.ImoocAuthenticationFailureHandler;
import com.yunfeng.app.authentication.ImoocAuthenticationSuccessHandler;
import com.yunfeng.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yunfeng.security.core.constants.SecurityConstants;
import com.yunfeng.security.core.properties.SecurityProperties;
import com.yunfeng.security.core.social.openid.OpenIdAuthenticationSecurityConfig;
import com.yunfeng.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * <p>
 * 资源服务器
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-26
 */

@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Autowired
    private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler);

        //http.apply(validateCodeSecurityConfig)
        //        .and()
        http.apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(imoocSocialSecurityConfig)
                .and()
            .apply(openIdAuthenticationSecurityConfig)
                .and()
            .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
                        "/user/register")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }



}
