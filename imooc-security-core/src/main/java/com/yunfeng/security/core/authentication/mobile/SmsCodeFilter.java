package com.yunfeng.security.core.authentication.mobile;

import com.yunfeng.security.core.properties.SecurityProperties;
import com.yunfeng.security.core.validate.ValidateProcessor;
import com.yunfeng.security.core.validate.code.ValidateCode;
import com.yunfeng.security.core.validate.code.image.ImageCode;
import com.yunfeng.security.core.validate.code.sms.SmsCode;
import com.yunfeng.security.core.validate.exception.ValidateCodeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private static final String SUBMIT_FORM_DATA_PATH = "/authentication/mobile";

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(), ",");
        if(ArrayUtils.isNotEmpty(configUrls)) {
            for(String configUrl : configUrls) {
                urls.add(configUrl);
            }
        }

        // 登录的链接是必须要进行验证码验证的
        urls.add(SUBMIT_FORM_DATA_PATH);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            // 如果实际访问的URL可以与用户在YML配置文件中配置的相同，那么就进行验证码校验
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 验证码校验逻辑
     *
     * @param request 请求
     * @throws ServletRequestBindingException 请求异常
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 从session中获取图片验证码
        ValidateCode smsCodeInSession = (SmsCode) sessionStrategy.getAttribute(request, ValidateProcessor.SESSION_KEY_PREFIX + "SMS");
        // 从请求中获取用户填写的验证码
        String imageCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
        if (StringUtils.isBlank(imageCodeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (null == smsCodeInSession) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (smsCodeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateProcessor.SESSION_KEY_PREFIX + "SMS");
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(imageCodeInRequest, smsCodeInSession.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }
        // 验证成功，删除session中的验证码
        sessionStrategy.removeAttribute(request, ValidateProcessor.SESSION_KEY_PREFIX + "SMS");
    }
}

