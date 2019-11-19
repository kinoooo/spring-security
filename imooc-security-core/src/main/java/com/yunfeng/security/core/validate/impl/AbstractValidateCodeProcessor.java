package com.yunfeng.security.core.validate.impl;

import com.yunfeng.security.core.validate.ValidateProcessor;
import com.yunfeng.security.core.validate.code.ValidateCode;
import com.yunfeng.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * <p>
 * 校验码处理器，封装不同校验码的处理逻辑
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-18
 */

public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateProcessor {
    /**
     * 操作session 的工具集
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;


    public void create(ServletWebRequest request) throws Exception {
        // 生成
        T validateCode = generate(request);
        // 保存
        save(request, validateCode);
        // 发送到客户端
        send(request, validateCode);

    }

    /**
     * 生成校验码
     */
    @SuppressWarnings("unchecked")
    private T generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (T) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码
     */
    private void save(ServletWebRequest request, T validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    /**
     * 发送校验码 由子类实现
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, T validateCode) throws Exception;

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
