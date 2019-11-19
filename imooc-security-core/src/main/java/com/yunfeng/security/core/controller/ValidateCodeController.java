package com.yunfeng.security.core.controller;

import com.yunfeng.security.core.validate.ValidateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 图形验证码接口
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-12
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateProcessor> validateProcessor;



    @GetMapping("/code/{type}")
    public void createImageCode(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable String type) throws Exception {
        validateProcessor.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
    }


}
