package com.yunfeng.validator;

import com.yunfeng.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-10
 */

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        System.out.println(value);
        return false;
    }

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my init");
    }
}
