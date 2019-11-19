package com.yunfeng.exception;

import lombok.Data;
import org.mockito.internal.matchers.Or;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-10
 */

@Data
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("user not exist" + id);
        this.id = id;
    }


}
