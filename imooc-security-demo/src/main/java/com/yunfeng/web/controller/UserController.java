package com.yunfeng.web.controller;

import com.yunfeng.dto.User;
import com.yunfeng.exception.UserNotExistException;
import com.yunfeng.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-09
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/me")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {

        String authorization = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(authorization, "bearer ");
        String jwtSigningKey = securityProperties.getOauth2().getJwtSigningKey();
        // 生成的时候使用的是 org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
        // 源码里面把signingkey变成utf8了
        // JwtAccessTokenConverter类，解析出来是一个map
        // 所以这个自带的JwtAccessTokenConverter对象也是可以直接用来解析的
        byte[] bytes = jwtSigningKey.getBytes("utf-8");
        Claims body = Jwts.parser().setSigningKey(bytes).parseClaimsJws(token).getBody();
        String company = (String) body.get("company");
        log.info("公司名称:{}",company);
        return body;

    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "用户查询服务")
    public User getInfo(@PathVariable String id){
        //throw new UserNotExistException(id);
        System.out.println("进入getInfo 方法");
        User user = new User();
        user.setUsername("yunfeng");
        return user;
    }


    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if(errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getUsername());
        System.out.println(user.getBirthday());
        user.setId(1L);
        return user;
    }

    @PostMapping("/register")
    public void register(User user, HttpServletRequest request) {
        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));

    }
}
