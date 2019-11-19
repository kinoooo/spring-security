package com.yunfeng.web.controller;

import com.yunfeng.dto.User;
import com.yunfeng.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
        //return SecurityContextHolder.getContext().getAuthentication();
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
}
