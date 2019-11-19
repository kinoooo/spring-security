package com.yunfeng.dto;

import com.yunfeng.validator.MyConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-09
 */
@Data
public class User {

    public interface UserSimpleView {

    }
    public interface UserDetailView extends UserSimpleView{

    }

    private Long id;

    @MyConstraint(message = "这是一个测试校验")
    private String username;

    @NotBlank(message = "不能为空")
    private String password;

    private Date birthday;
}
