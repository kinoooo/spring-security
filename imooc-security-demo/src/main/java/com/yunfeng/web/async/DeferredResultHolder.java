package com.yunfeng.web.async;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-11
 */
@Component
@Getter
@Setter
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> map = new HashMap<>();


}
